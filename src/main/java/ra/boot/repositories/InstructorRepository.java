package ra.boot.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.boot.dto.InstructorDto;
import ra.boot.models.Instructor;

import java.util.List;

@Repository
public interface  InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query(value = "from Instructor where name ilike concat('%', :key ,'%') ")
    List<Instructor> findByName(@Param("key") String name);

    @Transactional
    @Modifying// dinh nghia kieu tra ve
    @Query(value = "delete from Instructor where email = :key")
    void deleteInstructorByEmail(@Param("key") String email);

    @Query(value = "from Instructor where email= :key")
    Instructor existsInstructorByEmail(@Param("key") String email);

    @Query(value = "from Instructor  where email ilike :key")
    List<Instructor> findAllByEmail(String email);

    @Query(value = "select new ra.boot.dto.InstructorDto(i.name, i.email) from Instructor i")
    List<InstructorDto> findAllInstructorDto();
}
