package ma.mundiapolis.patientmvc.security.repo;

import ma.mundiapolis.patientmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
