package com.amran.todo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.amran.todo.model.ToDoEntity;
import com.amran.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    ToDoRepository toDoRepository;

    public String saveDailyNote(ToDoEntity toDoEntity) {
        try {
            toDoRepository.save(toDoEntity);
        }catch (Exception ex){
            LOGGER.error("Error during add note. "+ex.getMessage());
            return "Error during add note.";
        }
        return "Note add successful";
    }

    public List<ToDoEntity> getAllToDoDatas() {
        return toDoRepository.findAll();
    }

    public Optional<ToDoEntity> getToDoEntityBYId(Long id){
        return toDoRepository.findById(id);
    }

    public String deleteDailyNote(ToDoEntity toDoEntity){
        try {
            toDoRepository.delete(toDoEntity);
        }catch (Exception ex){
            LOGGER.error("Error during delete note. "+ex.getMessage());
            return "Error during delete note.";
        }
        return "Note delete successful";
    }
}
