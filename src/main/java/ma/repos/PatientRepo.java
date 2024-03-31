package ma.repos;

import ma.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsIgnoreCase(String nom, Pageable pageable);
}
