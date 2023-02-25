package com.pme.controller;

import com.pme.dto.GetAllMembersDTO;
import com.pme.dto.GetMemberDTO;
import com.pme.dto.NewMemberRequestDTO;
import com.pme.dto.UpdateMemberRequestDTO;
import com.pme.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<GetMemberDTO> addMember (@Valid @RequestBody NewMemberRequestDTO newMember) {
        return memberService.addMember(newMember);
    }

    @GetMapping
    public ResponseEntity<GetAllMembersDTO> getAllMembers (
            @RequestParam(defaultValue = "1") int page, //for pagination
            @RequestParam(defaultValue = "10") int limit, //for pagination
            @RequestParam(defaultValue = "") String firstName, //for wildcard sorting
            @RequestParam(defaultValue = "") String lastName, //for wildcard sorting
            @RequestParam(defaultValue = "") String email) { //for wildcard sorting

        return memberService.getAllMembers(limit, page, firstName, lastName, email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberDTO> getMember (@PathVariable Long id) {
        return memberService.getMember(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetMemberDTO> updateMember (@PathVariable Long id,
                                                          @Valid @RequestBody UpdateMemberRequestDTO updatedMember) {
        return memberService.updateMember(id, updatedMember);
    }

    @PutMapping("/{id}/updateenabledstatus")
    public ResponseEntity<GetMemberDTO> updateMemberEnabledStatus (@PathVariable Long id,
                                                                       @RequestParam boolean enabled) {
        return memberService.updateMemberEnabledStatus(id, enabled);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember (@PathVariable Long id) {
        return memberService.deleteMember(id);
    }
}
