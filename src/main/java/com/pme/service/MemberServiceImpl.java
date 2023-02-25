package com.pme.service;

import com.pme.dto.*;
import com.pme.entity.Member;
import com.pme.entity.TechStack;
import com.pme.repo.MemberRepo;
import com.pme.repo.StackRepo;
import com.pme.utils.AdditionUtils;
import com.pme.utils.ExtractionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepo memberRepo;
    private final StackRepo stackRepo;
    private final AdditionUtils additionUtils;
    private final ExtractionUtils extractionUtils;

    @Autowired
    public MemberServiceImpl(MemberRepo memberRepo, AdditionUtils additionUtils,
                             ExtractionUtils extractionUtils, StackRepo stackRepo) {
        this.memberRepo = memberRepo;
        this.additionUtils = additionUtils;
        this.extractionUtils = extractionUtils;
        this.stackRepo = stackRepo;
    }

    @Transactional
    @Override
    public ResponseEntity<GetMemberDTO> addMember(NewMemberRequestDTO newMember) {
        String checkIfEmailOrPhoneExists = additionUtils
                .checkIfEmailOrPhoneExists(newMember.getEmail(), newMember.getPhoneNo(), memberRepo);

        if (checkIfEmailOrPhoneExists != null)
            return ResponseEntity
                    .status(400)
                    .body(new GetMemberDTO(checkIfEmailOrPhoneExists, null));

        //Check if both entered passwords match
        if (!additionUtils.PasswordMatch
                (newMember.getPassword(), newMember.getConfirmPassword()))
            return ResponseEntity
                    .status(400)
                    .body(new GetMemberDTO("Passwords do not match", null));

        Member member = additionUtils.newMemberSetter(newMember);
        Member createdMember = memberRepo.save(member); //Saving to DB

        addStackToMember(newMember.getStack(), createdMember); //Adding tech stack to member and saving

        //extracting required response(There's no password in response)
        MemberDetails response = extractionUtils.extractMemberDetails(createdMember);

        return ResponseEntity
                .status(201)
                .body(new GetMemberDTO("New Member Created Successfully", response));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void addStackToMember(Collection<String> stacks, Member createdMember) {
        List<TechStack> collectedStacks = stacks.stream()
                                            .map(stack -> stackRepo.findByName(stack))
                                            .collect(Collectors.toList());
        createdMember.getStack().addAll(collectedStacks);
    }

    @Override
    public ResponseEntity<GetAllMembersDTO> getAllMembers(int limit, int page, String firstName,
                                              String lastName, String email) {

        //Checking request params for sorting
        if (!firstName.isEmpty() || !lastName.isEmpty() || !email.isEmpty()) {
            firstName = (firstName.isEmpty()) ? "   " : firstName;
            lastName = (lastName.isEmpty()) ? "   " : lastName;
            email = (email.isEmpty()) ? "   " : email;
        }


        //Pagination Implementation
        page = (page < 1)? 1 : page;
        Pageable pageable = PageRequest.of(page-1, limit)
                .withSort(Sort.by(Sort.Direction.DESC, "date_registered")); //Sort by Date Registered

        //Fetching from database
        List<Member> allMembers = memberRepo.getAllMembers(firstName, lastName, email, pageable);

        //If response is empty
        if (allMembers.isEmpty())
            return ResponseEntity.status(404)
                    .body(new GetAllMembersDTO("Members NOT FOUND", null));

        //Extracting to DTO
        List<MemberDetails> allMembersDetails = allMembers.stream()
                .map(member -> extractionUtils.extractMemberDetails(member))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(200)
                .body(new GetAllMembersDTO("Retrieved All Members Successfully", allMembersDetails));
    }

    @Override //Get A Member
    public ResponseEntity<GetMemberDTO> getMember(Long memberId) {
        MemberDetails response = extractionUtils.extractMemberDetails(memberRepo.findById(memberId).get());

        return ResponseEntity
                .status(200)
                .body(new GetMemberDTO("Retrieved Member's Details Successfully", response));
    }

    @Transactional
    @Override
    public ResponseEntity<GetMemberDTO> updateMember(Long memberId, UpdateMemberRequestDTO updatedMember) {
        //Check if email or phone number exists for other users
        String checkIfEmailOrPhoneExists = additionUtils
                .checkIfEmailOrPhoneExistsForUpdate(updatedMember.getEmail(), updatedMember.getPhoneNo(), memberRepo, memberId);

        if (checkIfEmailOrPhoneExists != null)
            return ResponseEntity
                    .status(400)
                    .body(new GetMemberDTO(checkIfEmailOrPhoneExists, null));

        //Trying to Update Member
        int updatedRow = memberRepo.updateMemberDetails(
                updatedMember.getFirstName(),
                updatedMember.getLastName(),
                updatedMember.getEmail(),
                updatedMember.getCountryCode(),
                updatedMember.getPhoneNo(),
                updatedMember.getAddress(),
                updatedMember.getTrack(),
                memberId
        );

        if (updatedRow == 0)  //If no row is NOT Found
            return ResponseEntity.status(404).body(new GetMemberDTO("Member NOT FOUND with id "+memberId, null));

        //Fetching UpdatedMember
        Member member = memberRepo.findById(memberId).get();
        List<TechStack> collectedStacks = updatedMember.getStack().stream()
                                .map(stack -> stackRepo.findByName(stack))
                                .collect(Collectors.toList());
        member.setStack(collectedStacks); //Setting updated Stack

        //Extracting to DTO
        MemberDetails response = extractionUtils.extractMemberDetails(member);

        return ResponseEntity
                .status(200)
                .body(new GetMemberDTO("Member's Details UPDATED Successfully", response));
    }

    @Override
    @Transactional
    public ResponseEntity<GetMemberDTO> updateMemberEnabledStatus(Long memberId, boolean enabled) {
        int status = 0;
        String updatedStatus = "DISABLED";

        if (enabled) {
            status = 1;
            updatedStatus = "ENABLED";
        }

        int updatedRow = memberRepo.updateMemberEnabledStatus(status, memberId);

        if (updatedRow == 0)
            return ResponseEntity.status(404).body(new GetMemberDTO("Member NOT FOUND with id "+memberId, null));

        MemberDetails response = extractionUtils.extractMemberDetails(memberRepo.findById(memberId).get());

        return ResponseEntity
                .status(200)
                .body(new GetMemberDTO("Member "+updatedStatus+" Successfully", response));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteMember(Long memberId) {
        memberRepo.deleteById(memberId);
        return ResponseEntity
                .status(200)
                .body("Member DELETED Successfully");
    }
}
