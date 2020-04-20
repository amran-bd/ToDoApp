package com.amran.todo.view;

import com.amran.todo.model.ToDoEntity;
import com.amran.todo.service.TodoService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Md. Amran Hossain
 */
@SpringComponent
@UIScope
public class ToDoNoteEditor extends VerticalLayout implements KeyNotifier {

    @Autowired
    private final TodoService todoService;

    private ToDoEntity toDoEntity;

    TextField itemName = new TextField("Item name");
    TextArea description = new TextArea("Description");
    DatePicker createDate = new DatePicker("Create Date");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button update = new Button("Update", VaadinIcon.REFRESH.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save,update, cancel, delete);
    Binder<ToDoEntity> binder = new Binder<>(ToDoEntity.class);
    private ChangeHandler changeHandler;

    public ToDoNoteEditor(TodoService todoService) {
        this.todoService = todoService;
        add(itemName, description,createDate, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        update.addClickListener(e -> update());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editTodo(toDoEntity));
        setVisible(false);
    }


    void delete() {
        todoService.deleteDailyNote(toDoEntity);
        changeHandler.onChange();
    }

    void save() {
        todoService.saveDailyNote(new ToDoEntity(itemName.getValue(),description.getValue(),createDate.getValue()));
        changeHandler.onChange();
    }

    void update() {
        toDoEntity.setItemName(itemName.getValue());
        toDoEntity.setDescription(description.getValue());
        toDoEntity.setCreatedDate(createDate.getValue());
        todoService.saveDailyNote(toDoEntity);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editTodo(ToDoEntity entity) {
        setVisible(true);
        itemName.setReadOnly(false);
        description.setReadOnly(false);
        if (entity == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = entity.getId() != null;
        if (persisted) {
            toDoEntity = todoService.getToDoEntityBYId(entity.getId()).get();
        } else {
            toDoEntity = entity;
        }
        save.setVisible(!persisted);
        update.setVisible(persisted);
        cancel.setVisible(persisted);
        binder.setBean(toDoEntity);
        itemName.focus();
        description.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
