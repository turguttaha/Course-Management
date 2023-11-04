package be.ucll.examenpracticum.repository;

import be.ucll.examenpracticum.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Page<Session> findByCourseId(Long courseId, Pageable pageable);
}
