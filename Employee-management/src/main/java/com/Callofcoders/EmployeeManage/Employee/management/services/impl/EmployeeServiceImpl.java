package com.Callofcoders.EmployeeManage.Employee.management.services.impl;

import com.Callofcoders.EmployeeManage.Employee.management.dtos.EmployeeDto;
import com.Callofcoders.EmployeeManage.Employee.management.entities.EmployeeEntity;
import com.Callofcoders.EmployeeManage.Employee.management.exceptions.ResourceNotFoundException;
import com.Callofcoders.EmployeeManage.Employee.management.repositories.EmployeeRepository;
import com.Callofcoders.EmployeeManage.Employee.management.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id " + id));
        return mapToDto(employeeEntity); // ✅ manual mapping
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity newEmployee = mapToEntity(employeeDto); // ✅ manual mapping
        EmployeeEntity savedEmployee = employeeRepository.save(newEmployee);
        return mapToDto(savedEmployee); // ✅ manual mapping
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto employeeDto) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id " + id));

        // ✅ no null check — replace everything client sends
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setSalary(employeeDto.getSalary());

        EmployeeEntity savedEmployee = employeeRepository.save(existingEmployee);
        return mapToDto(savedEmployee);
    }

    @Override
    public EmployeeDto patchById(Long id, EmployeeDto employeeDto) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id " + id));

        // ✅ null check — only update what client sends
        if(employeeDto.getName() != null) existingEmployee.setName(employeeDto.getName());
        if(employeeDto.getEmail() != null) existingEmployee.setEmail(employeeDto.getEmail());
        if(employeeDto.getSalary() != null) existingEmployee.setSalary(employeeDto.getSalary());

        EmployeeEntity savedEmployee = employeeRepository.save(existingEmployee);
        return mapToDto(savedEmployee);
    }

    @Override
    public void deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(entity -> mapToDto(entity)) // ✅ manual mapping
//              .map(this::mapToDto())             or use this
//              .map(employeeEntity ->modelMapper.map(employeeEntity,EmployeeDto.class))   or use modelMapper
                .collect(Collectors.toList());
    }

    // ✅ Entity → Dto
    private EmployeeDto mapToDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    // ✅ Dto → Entity
    private EmployeeEntity mapToEntity(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setSalary(dto.getSalary());
        // id skipped — DB generates it automatically
        return entity;
    }
}
