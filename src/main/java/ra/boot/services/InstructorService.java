package ra.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.boot.dto.InstructorDto;
import ra.boot.models.Instructor;
import ra.boot.repositories.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorDto> getAllInstructors() {
        return instructorRepository.findAllInstructorDto();
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor != null) {
            instructor.setName(instructorDetails.getName());
            instructor.setEmail(instructorDetails.getEmail());
            return instructorRepository.save(instructor);
        }
        return null;
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    // cơ chế phân trang và săps xếp

    //lop ho tro phan tranh: pageable
    public Page<Instructor> getInstructorByPaginate(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return instructorRepository.findAll(pageable);
    }

    public List<Instructor> getInstructorBySort(String field, String order){
        Sort sort;
        if(order.equalsIgnoreCase("asc")){
            sort = Sort.by(field).ascending();
        }else{
            sort= Sort.by(field).descending();
        }
        return instructorRepository.findAll(sort);
    }
}
