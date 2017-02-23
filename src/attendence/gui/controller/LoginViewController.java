/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.be.Student;
import attendence.be.Teacher;
import attendence.bll.PersonManager;
import attendence.gui.model.StudentModel;
import attendence.gui.model.TeacherModel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class LoginViewController implements Initializable {

    PersonManager manager;
    StudentModel studentModel;
    TeacherModel teacherModel;
    List<Student> Students;
    List<Teacher> Teachers;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private Button closeButton;
    @FXML
    private BorderPane bp;

    public LoginViewController()
    {
        this.studentModel = StudentModel.getInstance();
        this.teacherModel = TeacherModel.getInstance();
        this.manager = new PersonManager();
        Students = manager.getAllStudents();
        Teachers = manager.getAllTeachers();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleLogin()
    {
        if (!"".equals(txtUser.getText()) && !"".equals(txtPass.getText()))
        {
            try
            {
                checkLoginInformation();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkLoginInformation() throws IOException
    {
        String usernameInput = txtUser.getText();
        String passwordInput = txtPass.getText();
        for (Student student : Students)
        {
            if (usernameInput.equals(student.getUsername()) && passwordInput.equals(student.getPassword()))
            {
                studentModel.setCurrentUser(student);
                Stage primaryStage = (Stage) txtUser.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendence/gui/view/StudentView.fxml"));
                Parent root = loader.load();
                primaryStage.close();

                Stage newStage = new Stage(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(root));

                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(primaryStage);
                newStage.setTitle("Student");

                newStage.show();
                
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newStage.setX(event.getScreenX() - xOffset);
                newStage.setY(event.getScreenY() - yOffset);
            }
        });
                
                
            }
        }
        for (Teacher teacher : Teachers)
        {
            if (usernameInput.equals(teacher.getUsername()) && passwordInput.equals(teacher.getPassword()))
            {
                teacherModel.setCurrentUser(teacher);
                System.out.println(teacherModel.getCurrentUser().getUsername());
                Stage primaryStage = (Stage) txtUser.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendence/gui/view/TeacherView.fxml"));
                Parent root = loader.load();
                primaryStage.close();

                Stage newStage = new Stage(StageStyle.UNDECORATED);
                newStage.setScene(new Scene(root));

                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(primaryStage);
                newStage.setTitle("Teacher");

                newStage.show();
                
                
                
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newStage.setX(event.getScreenX() - xOffset);
                newStage.setY(event.getScreenY() - yOffset);
            }
            });
  
            }
        }
    }
    
    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
