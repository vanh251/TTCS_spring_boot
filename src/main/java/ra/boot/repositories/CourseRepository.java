package ra.boot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.boot.dto.CourseDTO;
import ra.boot.models.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //tim kiem theo status
    @Query(value = "from Course where lower(status) like lower(:status) ")
    List<Course> findAllByStatus(String status);

    //dem so ban ghi theo status
    @Query(value = "select count(c) from Course c where lower(c.status) like lower(:status)")
    int countCourseByStatus(String status);
    
    @Query(value = "select new ra.boot.dto.CourseDTO(c.id, c.title, c.status, i.name) from Course c left join c.instructor i where (:status is null or lower(c.status) like lower(concat('%', :status, '%')))")
    Page<CourseDTO> findCoursesWithPaginationAndProjection(
            @Param("status") String status, 
            Pageable pageable);
}
