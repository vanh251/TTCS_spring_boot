package ra.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.boot.models.Instructor;

@Repository
public interface  InstructorRepository extends JpaRepository<Instructor, Long> {
}
