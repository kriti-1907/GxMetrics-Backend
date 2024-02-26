package com.galaxe.metrics.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.galaxe.metrics.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByEmailAndPassword(String email, String password);
	 boolean existsByEmail(String email);
	 @Query(value = "SELECT * FROM Users WHERE MONTH(dob) = MONTH(:today) AND DAY(dob) = DAY(:today)", nativeQuery = true)
	    List<Users> findByDobMonthAndDobDay(@Param("today") String today);

}
