package by.krainet.krainet.test.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "tests")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testSeqGenerator")
    @SequenceGenerator(name = "testSeqGenerator", sequenceName = "test_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "test_directions",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id")
    )
    private List<Direction> applicableDirections;
}
