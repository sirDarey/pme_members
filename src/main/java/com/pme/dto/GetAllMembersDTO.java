package com.pme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @Getter @AllArgsConstructor
public class GetAllMembersDTO {
    private String message;
    private List<MemberDetails> data;
}
