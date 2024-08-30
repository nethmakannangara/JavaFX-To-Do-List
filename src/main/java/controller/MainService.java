package controller;

import javafx.collections.ObservableList;
import model.ToDoItem;
import model.ToDoList;

public interface MainService {
    ObservableList<ToDoItem> loadToDoList();

    boolean addTask(String date,String taskDescription);

    ObservableList<ToDoItem> loadDoneList();


    boolean addDoneList(String doneTaskDescription);

    void updateDoneList(String date, String time, String selectedToDoTask);
}
