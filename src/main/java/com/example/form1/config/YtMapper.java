package com.example.form1.config;

import com.example.form1.dto.YtCategoryDto;
import com.example.form1.dto.YtTaskDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.entity.YtTask;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface YtMapper {
    @Mappings({})
    YtCategoryDto maps(YtCategory source);

    @InheritInverseConfiguration
    YtCategory maps(YtCategoryDto dto);


    @Mappings({@Mapping(target = "idCategory", source = "category.id")})
    YtTaskDto maps(YtTask source);

    @InheritInverseConfiguration
    YtTask maps(YtTaskDto dto);

}
