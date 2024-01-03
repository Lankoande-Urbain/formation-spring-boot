package com.example.form1.config;

import com.example.form1.dto.YtCategoryDto;
import com.example.form1.dto.YtTaskDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.entity.YtTask;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-04T20:19:44+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class YtMapperImpl implements YtMapper {

    @Override
    public YtCategoryDto maps(YtCategory source) {
        if ( source == null ) {
            return null;
        }

        YtCategoryDto ytCategoryDto = new YtCategoryDto();

        ytCategoryDto.setId( source.getId() );
        ytCategoryDto.setName( source.getName() );

        return ytCategoryDto;
    }

    @Override
    public YtCategory maps(YtCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        YtCategory ytCategory = new YtCategory();

        if ( dto.getId() != null ) {
            ytCategory.setId( dto.getId() );
        }
        ytCategory.setName( dto.getName() );

        return ytCategory;
    }

    @Override
    public YtTaskDto maps(YtTask source) {
        if ( source == null ) {
            return null;
        }

        YtTaskDto ytTaskDto = new YtTaskDto();

        ytTaskDto.setIdCategory( sourceCategoryId( source ) );
        ytTaskDto.setId( source.getId() );
        ytTaskDto.setTitle( source.getTitle() );
        ytTaskDto.setDescription( source.getDescription() );
        ytTaskDto.setStatus( source.getStatus() );

        return ytTaskDto;
    }

    @Override
    public YtTask maps(YtTaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        YtTask ytTask = new YtTask();

        ytTask.setCategory( ytTaskDtoToYtCategory( dto ) );
        ytTask.setId( dto.getId() );
        ytTask.setTitle( dto.getTitle() );
        ytTask.setDescription( dto.getDescription() );
        ytTask.setStatus( dto.getStatus() );

        return ytTask;
    }

    private Long sourceCategoryId(YtTask ytTask) {
        if ( ytTask == null ) {
            return null;
        }
        YtCategory category = ytTask.getCategory();
        if ( category == null ) {
            return null;
        }
        long id = category.getId();
        return id;
    }

    protected YtCategory ytTaskDtoToYtCategory(YtTaskDto ytTaskDto) {
        if ( ytTaskDto == null ) {
            return null;
        }

        YtCategory ytCategory = new YtCategory();

        if ( ytTaskDto.getIdCategory() != null ) {
            ytCategory.setId( ytTaskDto.getIdCategory() );
        }

        return ytCategory;
    }
}
