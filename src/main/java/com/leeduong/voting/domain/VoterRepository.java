package com.leeduong.voting.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository interface for {@link Voter}s.
 * 
 * @author Lee Duong
 */
@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

	List<Voter> findById(long id);
	
	@Modifying
	@Transactional
	@Query("update Voter v set v.voteCount = v.voteCount + 1 where v.id = :id and v.voteCount < :maxVotes")
	int updateVoteCount(@Param("id") long id, @Param("maxVotes") int maxVotes);
}
