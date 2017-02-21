/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.be.Absence;
import attendence.bll.PersonManager;
import attendence.gui.model.StudentModel;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class StudentViewController implements Initializable {

    StudentModel model;
    PersonManager manager = new PersonManager();
    List<Absence> absences;
    
    @FXML
    private Label lblUser;
    @FXML
    private ListView<Date> listMissedClasses;

    public StudentViewController()
    {
        this.absences = manager.getAllAbsence();
        this.model = StudentModel.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (Absence absence : absences)
        {
            if(model.getCurrentUser().getId() == absence.getId()){
                model.addMissedClass(absence);
            }
        }
        lblUser.setText(model.getCurrentUser().getFirstName()+" "+model.getCurrentUser().getLastName());
        listMissedClasses.setItems(model.getMissedClassesAsString());
    }    
    
}
