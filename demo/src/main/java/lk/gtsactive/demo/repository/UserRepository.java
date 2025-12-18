package lk.gtsactive.demo.repository;


import lk.gtsactive.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    boolean existsByRole_Id(Long roleId);
}