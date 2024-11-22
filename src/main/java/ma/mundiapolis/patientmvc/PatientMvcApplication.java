package ma.mundiapolis.patientmvc;

import ma.mundiapolis.patientmvc.entities.Patient;
import ma.mundiapolis.patientmvc.repositories.PatientRepository;
import ma.mundiapolis.patientmvc.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args->{
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1 == null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2 == null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3 == null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
            );
        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user10", "1234", "user10@gmail.com", "1234");
            accountService.addNewUser("user20", "1234", "user20@gmail.com", "1234");
            accountService.addNewUser("admin3", "1234", "admin3@gmail.com", "1234");

            accountService.addRoleToUser("user10", "USER");
            accountService.addRoleToUser("user20", "USER");
            accountService.addRoleToUser("admin3", "USER");
            accountService.addRoleToUser("admin3", "ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
