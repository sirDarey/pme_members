package com.pme.service;

import com.pme.dto.GetAllMembersDTO;
import com.pme.dto.GetMemberDTO;
import com.pme.dto.NewMemberRequestDTO;
import com.pme.dto.UpdateMemberRequestDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<GetMemberDTO> addMember(NewMemberRequestDTO newMember);

    ResponseEntity<GetAllMembersDTO> getAllMembers(int limit, int page, String firstName, String lastName, String email);

    ResponseEntity<GetMemberDTO> getMember(Long memberId);

    ResponseEntity<GetMemberDTO> updateMember(Long memberId, UpdateMemberRequestDTO updatedMember);

    ResponseEntity<GetMemberDTO> updateMemberEnabledStatus(Long memberId, boolean enabled);

    ResponseEntity<String> deleteMember(Long memberId);
}
