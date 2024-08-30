package model;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToDoList {

    private int id;
    private String date;
    private String taskDescription;
}
