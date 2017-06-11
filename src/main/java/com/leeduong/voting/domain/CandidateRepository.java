package com.leeduong.voting.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for {@link Candidate}s.
 * 
 * @author Lee Duong
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	List<Candidate> findById(long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Candidate c SET c.votes = c.votes + 1 WHERE c.id = :id")
	int updateVotes(@Param("id") long id);
}
