package by.krainet.krainet.test.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "candidates")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidateSeqGenerator")
    @SequenceGenerator(name = "candidateSeqGenerator", sequenceName = "candidate_seq", allocationSize = 1)
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;

    @Lob
    private byte[] photo;
    private String description;

    @Lob
    private byte[] cvFile;

    @ManyToMany
    @JoinTable(
            name = "candidate_directions",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id")
    )
    private List<Direction> possibleDirections;
}
