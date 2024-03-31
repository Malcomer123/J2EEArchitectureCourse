package ma;

import ma.models.Patient;
import ma.repos.PatientRepo;
import ma.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringCourseApplication  {
    private final PatientRepo patientRepo;

    public SpringCourseApplication(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCourseApplication.class, args);
    }

    @Bean
    public CommandLineRunner start() {
        return args -> {
            // Ajout des patients
            patientRepo.save(new Patient(null, "Hassan", new Date(), true, 100));
            patientRepo.save(new Patient(null, "Khalid", new Date(), false, 2000));
            patientRepo.save(new Patient(null, "Omar", new Date(), false, 2000));
            patientRepo.save(new Patient(null, "Salma", new Date(), true, 400));
            patientRepo.save(new Patient(null, "Yassine", new Date(), false, 2100));
            patientRepo.save(new Patient(null, "Younes", new Date(), true, 500));
            patientRepo.save(new Patient(null, "Zineb", new Date(), true, 300));
            // generate 30 entries like the saves you gave me with different examples
            String[] names = {"Mohammed", "Fatima", "Ahmed", "Khadija", "Abdullah", "Aisha", "Youssef", "Nadia", "Ali", "Salma"};

            // Generate 30 entries
            for (int i = 0; i < 30; i++) {
                String name = names[i % names.length]; // Cycle through the names
                Patient patient = new Patient(null, name, new Date(), i % 2 == 0, i * 100);
                patientRepo.save(patient);
            }

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
        };
    }

    /**
     * Le cas de JDBCUserDetailsManager Strategty
     */

//    @Bean
//    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        return args -> {
//            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
//            if(u1 == null)
//                jdbcUserDetailsManager.createUser(
//                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
//                );
//            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
//            if(u2 == null)
//                jdbcUserDetailsManager.createUser(
//                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
//                );
//            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin33");
//            if(u3 == null)
//                jdbcUserDetailsManager.createUser(
//                        User.withUsername("admin33").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
//                );
//        };
//    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user44", "1234", "user44@gmail.com", "1234");
            accountService.addNewUser("user55", "1234", "user55@gmail.com", "1234");
            accountService.addNewUser("admin23", "1234", "admin23@gmail.com", "1234");

            accountService.addRoleToUser("user44", "USER");
            accountService.addRoleToUser("user55", "USER");
            accountService.addRoleToUser("admin23", "ADMIN");
            accountService.addRoleToUser("admin23", "USER");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
