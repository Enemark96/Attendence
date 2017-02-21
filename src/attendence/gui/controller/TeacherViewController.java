/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.gui.model.TeacherModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class TeacherViewController implements Initializable {

    TeacherModel model;
    
    @FXML
    private Label lblUsername;

    public TeacherViewController()
    {
        this.model = TeacherModel.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUsername.setText(model.getCurrentUser().getFirstName()+" "+model.getCurrentUser().getLastName());
    }    
    
}
