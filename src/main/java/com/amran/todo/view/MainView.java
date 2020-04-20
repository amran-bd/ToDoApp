package com.amran.todo.view;

import com.amran.todo.model.ToDoEntity;
import com.amran.todo.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Route
@PWA(name = "ToDo Note", shortName = "Todo App", description = "This is an example ToDo Note.", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    final Grid<ToDoEntity> grid;
    @Autowired
    private final TodoService todoService;
    private final Button addNewBtn;

    public MainView(TodoService todoService, ToDoNoteEditor toDoNoteEditor) {
        this.todoService = todoService;
        this.addNewBtn = new Button("New ToDo", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(ToDoEntity.class);
        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid, toDoNoteEditor);

        grid.setHeight("200px");
        grid.setColumns("id","itemName", "description", "createdDate");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        listTodo();

        grid.asSingleSelect().addValueChangeListener(e -> { toDoNoteEditor.editTodo(e.getValue()); });
        addNewBtn.addClickListener(e -> toDoNoteEditor.editTodo(new ToDoEntity("", "",LocalDate.now())));
        toDoNoteEditor.setChangeHandler(() -> { toDoNoteEditor.setVisible(true);
            listTodo();
        });

    }

    private void listTodo(){
        List<ToDoEntity> entities = todoService.getAllToDoDatas();
        grid.setItems(entities);
    }

}
