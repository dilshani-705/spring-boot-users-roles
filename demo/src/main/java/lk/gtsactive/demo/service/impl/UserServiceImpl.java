package lk.gtsactive.demo.service.impl;


import lk.gtsactive.demo.dto.UserDTO;
import lk.gtsactive.demo.entity.RoleEntity;
import lk.gtsactive.demo.entity.UserEntity;
import lk.gtsactive.demo.repository.RoleRepository;
import lk.gtsactive.demo.repository.UserRepository;
import lk.gtsactive.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDTO create(UserDTO dto) {
        RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow();


        UserEntity user = new UserEntity();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(role);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));


        userRepository.save(user);
        dto.setId(user.getId());
        return dto;
    }


    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            dto.setRoleId(user.getRole().getId());
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public UserDTO getById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRoleId(user.getRole().getId());
        return dto;
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public UserDTO update(Long id, UserDTO dto) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        if (dto.getFirstName()!=null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName()!=null) user.setLastName(dto.getLastName());
        if (dto.getEmail()!=null) user.setEmail(dto.getEmail());
        if (dto.getPassword()!=null) user.setPassword(dto.getPassword());

        if (dto.getRoleId()!=null) {
            RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow();
            user.setRole(role);
        }

        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);

        UserDTO out = new UserDTO();
        out.setId(user.getId());
        out.setFirstName(user.getFirstName());
        out.setLastName(user.getLastName());
        out.setEmail(user.getEmail());
        out.setRoleId(user.getRole().getId());
        return out;
    }

}