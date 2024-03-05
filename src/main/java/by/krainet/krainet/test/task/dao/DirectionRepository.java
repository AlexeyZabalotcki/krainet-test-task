package by.krainet.krainet.test.task.dao;

import by.krainet.krainet.test.task.model.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Page<Direction> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
