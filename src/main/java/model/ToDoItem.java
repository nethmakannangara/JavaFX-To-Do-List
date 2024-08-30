package model;

import com.mysql.cj.conf.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ToDoItem {
    private String taskDescription;
    private final SimpleBooleanProperty isDone = new SimpleBooleanProperty(false);

    public ToDoItem(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public SimpleBooleanProperty isDoneProperty(){
        return isDone;
    }
}
