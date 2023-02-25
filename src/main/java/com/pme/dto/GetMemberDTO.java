package com.pme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter @AllArgsConstructor
public class GetMemberDTO {

    private String message;
    private MemberDetails data;
}
