package lk.gtsactive.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;


    @Column(unique = true)
    private String email;


    private String password;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    private Timestamp createdAt;
    private Timestamp updatedAt;
}