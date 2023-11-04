package be.ucll.examenpracticum.repository;

import be.ucll.examenpracticum.entity.Course;
import be.ucll.examenpracticum.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c JOIN c.people p WHERE p.id=?1")
    Page<Course> getCourseByPersonId(Long personId, Pageable pageable);

}
