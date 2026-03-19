package ra.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.boot.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
