package com.pme.utils;

import com.pme.dto.MemberDetails;
import com.pme.entity.Member;

public class ExtractionUtils {

	public MemberDetails extractMemberDetails (Member createdMember) {
		boolean memberEnabled = createdMember.getMemberEnabled() == 1? true : false;

		return new MemberDetails (
					createdMember.getMemberId(),
					createdMember.getFirstName(),
					createdMember.getLastName(),
					createdMember.getEmail(),
					"+"+String.valueOf(createdMember.getCountryCode())
							+String.valueOf(createdMember.getPhoneNo()),
					createdMember.getAddress(),
					createdMember.getTrack(),
					createdMember.getStack(),
					memberEnabled,
					createdMember.getLastLogin(),
					createdMember.getDateRegistered(),
					createdMember.getLastFailedLogin()
				);
	}
}
