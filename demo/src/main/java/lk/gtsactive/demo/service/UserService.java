package lk.gtsactive.demo.service;


import lk.gtsactive.demo.dto.UserDTO;


import java.util.List;


public interface UserService {
    UserDTO create(UserDTO dto);
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    void delete(Long id);
    UserDTO update(Long id, UserDTO dto);
}