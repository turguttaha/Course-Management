package be.ucll.examenpracticum.repository;

import be.ucll.examenpracticum.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
