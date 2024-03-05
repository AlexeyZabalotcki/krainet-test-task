package by.krainet.krainet.test.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "test_results")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testResultsSeqGenerator")
    @SequenceGenerator(name = "testResultsSeqGenerator", sequenceName = "test_results_seq", allocationSize = 1)
    private Long id;
    private LocalDate date;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "candidate_test")
    private CandidateTest candidateTest;
}
