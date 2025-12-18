package lk.gtsactive.demo.service;

import lk.gtsactive.demo.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    List<RoleDTO> getAll();
    RoleDTO getById(Long id);
    RoleDTO update(Long id, RoleDTO dto);
    void delete(Long id);
}

