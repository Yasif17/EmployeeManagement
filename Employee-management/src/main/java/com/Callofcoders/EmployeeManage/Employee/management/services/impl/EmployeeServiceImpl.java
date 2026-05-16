package com.Callofcoders.EmployeeManage.Employee.management.services.impl;

import com.Callofcoders.EmployeeManage.Employee.management.dtos.EmployeeDto;
import com.Callofcoders.EmployeeManage.Employee.management.entities.EmployeeEntity;
import com.Callofcoders.EmployeeManage.Employee.management.exceptions.ResourceNotFoundException;
import com.Callofcoders.EmployeeManage.Employee.management.repositories.EmployeeRepository;
import com.Callofcoders.EmployeeManage.Employee.management.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not found with id "+id));
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity newEmployee = modelMapper.map(employeeDto,EmployeeEntity.class);
        EmployeeEntity savingNewEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(savingNewEmployee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto employeeDto) {
        EmployeeEntity updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with id "+id));
        modelMapper.map(employeeDto,updateEmployee);
        EmployeeEntity savedUpdatedEmployee = employeeRepository.save(updateEmployee);
        return modelMapper.map(savedUpdatedEmployee,EmployeeDto.class) ;
    }

    @Override
    public void deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> listOfEmployees = employeeRepository.findAll();
        return listOfEmployees.stream()
                .map(employeeEntity -> mapToDto(employeeEntity))
         //     .map(this::mapToDto())             or use this
          //    .map(employeeEntity ->modelMapper.map(employeeEntity,EmployeeDto.class))   or use modelMapper
                .collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(EmployeeEntity employeeEntity){
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employeeEntity.getId());
        dto.setName(employeeEntity.getName());
        dto.setEmail(employeeEntity.getEmail());
        dto.setSalary(employeeEntity.getSalary());

        return dto;
    }

}
