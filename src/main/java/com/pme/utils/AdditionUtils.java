package com.pme.utils;

import com.pme.dto.NewMemberRequestDTO;
import com.pme.entity.Member;
import com.pme.repo.MemberRepo;
import jakarta.validation.Valid;

import java.util.Date;

public class AdditionUtils {

	public Member newMemberSetter(@Valid NewMemberRequestDTO newMember) {
		Member member = new Member(
				null,
				newMember.getFirstName(),
				newMember.getLastName(),
				newMember.getEmail(),
				newMember.getCountryCode(),
				newMember.getPhoneNo(),
				newMember.getAddress(),
				newMember.getTrack(),
				newMember.getPassword(),
				(byte)0,
				null,
				new Date(System.currentTimeMillis()),
				null
		);
		
		return member;
	}

	public String checkIfEmailOrPhoneExists(String email, Long phoneNo, MemberRepo memberRepo) {
		int countEmail = memberRepo.checkIfEmailExists(email);
		if (countEmail > 0)
			return "Email Already Exists";
		
		int countPhone = memberRepo.checkIfPhoneExists(phoneNo);
		if (countPhone > 0)
			return "Phone Number Already Exists";
		
		return null;
	}

	public boolean PasswordMatch (String password, String confirmPassword) {

		return password.equals(confirmPassword);
	}

    public String checkIfEmailOrPhoneExistsForUpdate(String email, Long phoneNo, MemberRepo memberRepo, Long memberId) {
		int countEmail = memberRepo.checkIfEmailExistsForUpdate(email, memberId);
		if (countEmail > 0)
			return "Email Already Exists";

		int countPhone = memberRepo.checkIfPhoneExistsForUpdate(phoneNo, memberId);
		if (countPhone > 0)
			return "Phone Number Already Exists";

		return null;
    }
}
