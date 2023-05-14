package za.co.bigsim.bankingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.bigsim.bankingApi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
