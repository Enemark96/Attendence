/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.be.Absence;
import attendence.be.Student;
import attendence.bll.PersonManager;
import attendence.gui.model.TeacherModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class TeacherViewController implements Initializable {

    TeacherModel model;
    PersonManager manager;
    
    @FXML
    private Label lblUsername;
    @FXML
    private TableView<Student> tblStudentAbs;
    @FXML
    private TableColumn<Student, String> colStudent;
    @FXML
    private TableColumn<Student, String> colAbsence;
    
    List<Student> studentList;
    List<Absence> absenceList;
    ObservableList<Student> allStudents;
    

    public TeacherViewController()
    {
        this.manager = new PersonManager();
        this.studentList = manager.getAllStudents();
        this.absenceList = manager.getAllAbsence();
        this.allStudents = FXCollections.observableArrayList();
        this.model = TeacherModel.getInstance();
        allStudents.addAll(studentList);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUsername.setText(model.getCurrentUser().getFirstName()+" "+model.getCurrentUser().getLastName());
        tblStudentAbs.setItems(allStudents);
        colStudent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        for (Student student : studentList)
        {
            int x = 0;
            for (Absence absence : absenceList)
            {
                if(student.getId() == absence.getStudentId())
                {
                    x++;
                }
            }
            student.setAmountOfAbsence(x);
        }
        colAbsence.setCellValueFactory(new PropertyValueFactory<>("amountOfAbsence"));
    }    
    
}
