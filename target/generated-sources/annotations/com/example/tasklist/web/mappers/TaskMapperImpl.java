package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.task.Task;
import com.example.tasklist.web.dto.task.TaskDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T13:19:20+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskDto d) {
        if ( d == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( d.getId() );
        task.setTitle( d.getTitle() );
        task.setDescription( d.getDescription() );
        task.setStatus( d.getStatus() );
        task.setExpirationDate( d.getExpirationDate() );
        List<String> list = d.getImages();
        if ( list != null ) {
            task.setImages( new ArrayList<String>( list ) );
        }

        return task;
    }

    @Override
    public TaskDto toDto(Task e) {
        if ( e == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId( e.getId() );
        taskDto.setTitle( e.getTitle() );
        taskDto.setDescription( e.getDescription() );
        taskDto.setStatus( e.getStatus() );
        taskDto.setExpirationDate( e.getExpirationDate() );
        List<String> list = e.getImages();
        if ( list != null ) {
            taskDto.setImages( new ArrayList<String>( list ) );
        }

        return taskDto;
    }

    @Override
    public List<TaskDto> toDto(List<Task> e) {
        if ( e == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( e.size() );
        for ( Task task : e ) {
            list.add( toDto( task ) );
        }

        return list;
    }

    @Override
    public List<Task> toEntity(List<TaskDto> d) {
        if ( d == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( d.size() );
        for ( TaskDto taskDto : d ) {
            list.add( toEntity( taskDto ) );
        }

        return list;
    }
}
