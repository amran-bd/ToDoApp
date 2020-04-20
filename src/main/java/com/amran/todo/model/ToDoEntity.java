package com.amran.todo.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Md. Amran Hossain
 */
@Entity
@Table(name = "user_todo_info")
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String description;
    private LocalDate createdDate;

    public ToDoEntity() {
    }

    public ToDoEntity(String itemName, String description, LocalDate createdDate) {
        this.itemName = itemName;
        this.description = description;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public ToDoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ToDoEntity setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToDoEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ToDoEntity setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
