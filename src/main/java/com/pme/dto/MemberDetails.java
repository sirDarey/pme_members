package com.pme.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pme.entity.TechStack;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor @Getter
public class MemberDetails {

    private Long memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String address;
    private String track;
    private Collection<TechStack> stack;
    private Boolean memberEnabled;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date lastLogin;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date dateRegistered;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date lastFailedLogin;

    public MemberDetails(Long memberId, String firstName, String lastName,
                         String email, String phoneNo, String address, String track,
                         Collection<TechStack> stack, Boolean memberEnabled, Date lastLogin,
                         Date dateRegistered, Date lastFailedLogin) {

        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.track = track;
        this.stack = stack;
        this.memberEnabled = memberEnabled;
        this.lastLogin = lastLogin;
        this.dateRegistered = dateRegistered;
        this.lastFailedLogin = lastFailedLogin;
    }
}
