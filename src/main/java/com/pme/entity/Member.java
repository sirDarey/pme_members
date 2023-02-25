package com.pme.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "members") @Setter @Getter @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private Integer countryCode;
    @Column(unique = true)
    private Long phoneNo;
    private String address;
    private String track;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "members_stack",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "stack_id"))
    private Collection<TechStack> stack = new ArrayList<>();
    private String password;
    private Byte memberEnabled;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = Shape.STRING)
    private Date lastLogin;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = Shape.STRING)
    private Date dateRegistered;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", shape = Shape.STRING)
    private Date lastFailedLogin;

    public Member(Long memberId, String firstName, String lastName, String email, Integer countryCode,
                  Long phoneNo, String address, String track, Collection<TechStack> stack, Byte memberEnabled,
                  Date lastLogin, Date dateRegistered, Date lastFailedLogin) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNo = phoneNo;
        this.address = address;
        this.track = track;
        this.stack = stack;
        this.memberEnabled = memberEnabled;
        this.lastLogin = lastLogin;
        this.dateRegistered = dateRegistered;
        this.lastFailedLogin = lastFailedLogin;
    }

    public Member(Long memberId, String firstName, String lastName, String email,
                  Integer countryCode, Long phoneNo, String address, String track,
                  String password, Byte memberEnabled, Date lastLogin, Date dateRegistered,
                  Date lastFailedLogin) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNo = phoneNo;
        this.address = address;
        this.track = track;
        this.password = password;
        this.memberEnabled = memberEnabled;
        this.lastLogin = lastLogin;
        this.dateRegistered = dateRegistered;
        this.lastFailedLogin = lastFailedLogin;
    }
}

