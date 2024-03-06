package by.krainet.krainet.test.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "directions")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directionSeqGenerator")
    @SequenceGenerator(name = "directionSeqGenerator", sequenceName = "direction_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "possibleDirections")
    private List<Candidate> candidates;
}
