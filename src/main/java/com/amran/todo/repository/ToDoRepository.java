package com.amran.todo.repository;

import com.amran.todo.model.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : Amran Hosssain on 4/13/2020
 */
public interface ToDoRepository  extends JpaRepository<ToDoEntity,Long> {
}
