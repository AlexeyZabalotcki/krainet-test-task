package by.krainet.krainet.test.task.dao;

import by.krainet.krainet.test.task.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> , JpaSpecificationExecutor<Candidate> {
}
