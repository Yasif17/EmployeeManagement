package com.Callofcoders.EmployeeManage.Employee.management.config;

import com.Callofcoders.EmployeeManage.Employee.management.dtos.EmployeeDto;
import com.Callofcoders.EmployeeManage.Employee.management.entities.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // ✅ Dto → Entity (for save/update) — skip id
        mapper.typeMap(EmployeeDto.class, EmployeeEntity.class)
                .addMappings(m -> m.skip(EmployeeEntity::setId));

        // ✅ Entity → Dto (for getAllEmployees) — map all fields
        mapper.typeMap(EmployeeEntity.class, EmployeeDto.class);

        return mapper;
    }


}
