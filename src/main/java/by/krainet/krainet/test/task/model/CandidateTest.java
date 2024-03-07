package by.krainet.krainet.test.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
//@ToString
@Table(name = "candidate_tests")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidateTestSeqGenerator")
    @SequenceGenerator(name = "candidateTestSeqGenerator", sequenceName = "candidate_test_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "candidateTest", cascade = CascadeType.PERSIST)
    private List<TestResult> testResults;
}
