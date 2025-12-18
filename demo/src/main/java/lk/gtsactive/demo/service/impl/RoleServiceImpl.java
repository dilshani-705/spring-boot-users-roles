package lk.gtsactive.demo.service.impl;

import lk.gtsactive.demo.dto.RoleDTO;
import lk.gtsactive.demo.entity.RoleEntity;
import lk.gtsactive.demo.repository.RoleRepository;
import lk.gtsactive.demo.repository.UserRepository;
import lk.gtsactive.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public RoleDTO create(RoleDTO dto) {
        RoleEntity role = new RoleEntity();
        role.setName(dto.getName());
        roleRepository.save(role);
        dto.setId(role.getId());
        return dto;
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll().stream().map(r -> {
            RoleDTO dto = new RoleDTO();
            dto.setId(r.getId());
            dto.setName(r.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getById(Long id) {
        RoleEntity r = roleRepository.findById(id).orElseThrow();
        RoleDTO dto = new RoleDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        return dto;
    }

    @Override
    public RoleDTO update(Long id, RoleDTO dto) {
        RoleEntity role = roleRepository.findById(id).orElseThrow();
        if (dto.getName() != null) role.setName(dto.getName());
        roleRepository.save(role);
        RoleDTO out = new RoleDTO();
        out.setId(role.getId());
        out.setName(role.getName());
        return out;
    }

    @Override
    public void delete(Long roleId) {
        if (userRepository.existsByRole_Id(roleId)) {
            throw new RuntimeException("Cannot delete role. Users are assigned to this role.");
        }
        roleRepository.deleteById(roleId);
    }
}
