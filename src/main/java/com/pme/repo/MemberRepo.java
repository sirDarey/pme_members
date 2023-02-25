package com.pme.repo;

import com.pme.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    @Query(nativeQuery = true,
            value = "SELECT COUNT(email) FROM members WHERE email=?1")
    int checkIfEmailExists(String email);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(phone_no) FROM members WHERE phone_no=?1")
    int checkIfPhoneExists(Long phoneNo);

    @Query (nativeQuery = true,
            value = "SELECT * FROM members WHERE first_name LIKE %?1% OR last_name LIKE %?2% " +
            "OR email LIKE %?3%")
    List<Member> getAllMembers(@Param("firstName") String firstName,
                               @Param("lastName") String lastName,
                               @Param("email") String email, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE members SET"
                    + " first_name=?1,"
                    + " last_name=?2,"
                    + " email=?3,"
                    + " country_code=?4,"
                    + " phone_no=?5,"
                    + " address=?6,"
                    + " track=?7"
                    + " WHERE member_id=?8")
    int updateMemberDetails(String firstName, String lastName, String email, Integer countryCode, Long phoneNo, String address, String track, Long memberId);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE members SET member_enabled=?1 WHERE member_id=?2")
    int updateMemberEnabledStatus(int status, Long memberId);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(email) FROM members WHERE email=?1 AND member_id !=?2")
    int checkIfEmailExistsForUpdate(String email, Long memberId);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(phone_no) FROM members WHERE phone_no=?1 AND member_id !=?2")
    int checkIfPhoneExistsForUpdate(Long phoneNo, Long memberId);
}
