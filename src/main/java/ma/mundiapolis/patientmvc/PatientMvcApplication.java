package ma.mundiapolis.patientmvc;

import ma.mundiapolis.patientmvc.entities.Patient;
import ma.mundiapolis.patientmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "Hakim", new Date(), false, 12));
            patientRepository.save(new Patient(null, "Othmane", new Date(), true, 321));
            patientRepository.save(new Patient(null, "Oussama", new Date(), true, 65));
            patientRepository.save(new Patient(null, "Hicham", new Date(), false, 32));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

}
