package za.co.bigsim.bankingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.bigsim.bankingApi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
