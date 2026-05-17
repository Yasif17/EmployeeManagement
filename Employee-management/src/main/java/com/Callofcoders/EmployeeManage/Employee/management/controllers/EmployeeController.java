package com.Callofcoders.EmployeeManage.Employee.management.controllers;

import com.Callofcoders.EmployeeManage.Employee.management.dtos.EmployeeDto;
import com.Callofcoders.EmployeeManage.Employee.management.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> listOfEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(listOfEmployees);
    }



    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto createNewEmployeeDto = employeeService.createNewEmployee(employeeDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createNewEmployeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long id,@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto updateEmployee = employeeService.updateById(id,employeeDto);
        return ResponseEntity.ok(updateEmployee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> patchEmployeeById(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
        EmployeeDto patchEmployeeDto = employeeService.patchById(id,employeeDto);
        return ResponseEntity.ok(patchEmployeeDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") Long employeeId){
        employeeService.deleteById(employeeId);
        return ResponseEntity.noContent().build();
    }


}
