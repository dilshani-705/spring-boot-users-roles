package lk.gtsactive.demo.repository;


import lk.gtsactive.demo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}