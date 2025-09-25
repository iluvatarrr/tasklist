package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.task.TaskImage;
import com.example.tasklist.web.dto.task.TaskImageDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T13:19:21+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TaskImageMapperImpl implements TaskImageMapper {

    @Override
    public TaskImage toEntity(TaskImageDto d) {
        if ( d == null ) {
            return null;
        }

        TaskImage taskImage = new TaskImage();

        taskImage.setFile( d.getFile() );

        return taskImage;
    }

    @Override
    public TaskImageDto toDto(TaskImage e) {
        if ( e == null ) {
            return null;
        }

        TaskImageDto taskImageDto = new TaskImageDto();

        taskImageDto.setFile( e.getFile() );

        return taskImageDto;
    }

    @Override
    public List<TaskImageDto> toDto(List<TaskImage> e) {
        if ( e == null ) {
            return null;
        }

        List<TaskImageDto> list = new ArrayList<TaskImageDto>( e.size() );
        for ( TaskImage taskImage : e ) {
            list.add( toDto( taskImage ) );
        }

        return list;
    }

    @Override
    public List<TaskImage> toEntity(List<TaskImageDto> d) {
        if ( d == null ) {
            return null;
        }

        List<TaskImage> list = new ArrayList<TaskImage>( d.size() );
        for ( TaskImageDto taskImageDto : d ) {
            list.add( toEntity( taskImageDto ) );
        }

        return list;
    }
}
