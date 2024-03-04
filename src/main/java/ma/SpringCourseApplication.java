package ma;

import ma.models.Patient;
import ma.repos.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner {
    @Autowired
    private PatientRepo patientRepo;
    public static void main(String[] args) {
        SpringApplication.run(SpringCourseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Ajout des patients
        patientRepo.save(new Patient(null, "Hassan", new Date(), true, 10, null));
        patientRepo.save(new Patient(null, "Khalid", new Date(), false, 0,null));
        patientRepo.save(new Patient(null, "Omar", new Date(), false, 0, null));
        patientRepo.save(new Patient(null, "Salma", new Date(), true, 40, null));
        patientRepo.save(new Patient(null, "Yassine", new Date(), false, 0, null));
        patientRepo.save(new Patient(null, "Younes", new Date(), true, 50, null));

        // Consulter tous les patients
        List<Patient> patients = patientRepo.findAll();
        patients.forEach(System.out::println);

        // Consulter un patient
        System.out.println("******************************");
        Patient patient = patientRepo.findById(1L).get();
        System.out.println(patient);

        // mettre a jour un patient par mot clé
        System.out.println("******************************");
        patient.setNom("Achraf");
        patient.setMalade(false);
        patient.setScore(0);
        patientRepo.save(patient);
        System.out.println(patientRepo.findById(1L).get());

        //supprimer un patient
        System.out.println("******************************");
        patientRepo.deleteById(2L);
        patients = patientRepo.findAll();
        patients.forEach(System.out::println);

    }
}
