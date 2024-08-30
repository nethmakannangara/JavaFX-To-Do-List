package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ToDoItem;
import model.ToDoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController implements MainService{

    @Override
    public ObservableList<ToDoItem> loadToDoList() {
        ObservableList<ToDoItem> toDoListsObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM todolist;";
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTM = connection.prepareStatement(SQL);
            ResultSet resultSet = psTM.executeQuery();
            while (resultSet.next()){
                toDoListsObservableList.add(new ToDoItem(resultSet.getString("Taskdescription")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toDoListsObservableList;
    }

    @Override
    public boolean addTask(String date,String taskDescription) {
        String SQL = "INSERT INTO todolist(Date,Taskdescription) VALUES(?,?);";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,date);
            psTm.setObject(2,taskDescription);
            return psTm.executeUpdate() > 0 ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ToDoItem> loadDoneList() {
        ObservableList<ToDoItem> doneListsObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM donelist;";
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTM = connection.prepareStatement(SQL);
            ResultSet resultSet = psTM.executeQuery();
            while (resultSet.next()){
                doneListsObservableList.add(new ToDoItem(resultSet.getString("Taskdescription")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doneListsObservableList;
    }

    @Override
    public boolean addDoneList(String doneTaskDescription) {
        String SQL = "DELETE FROM todolist WHERE Taskdescription LIKE ? ;";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,doneTaskDescription);
            return psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDoneList(String date, String time, String taskDescription) {
        String SQL = "INSERT INTO donelist(Date,Time,Taskdescription) VALUES(?,?,?);";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,date);
            psTm.setObject(2,time);
            psTm.setObject(3,taskDescription);
            psTm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
