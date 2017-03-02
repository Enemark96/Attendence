/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.be.Person;
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
 * @author Simon Birkedal, Stephan Fuhlendorff, Thomas Hansen & Jacob Enemark
 */
public class LoginViewController extends Dragable implements Initializable
{

    private final PersonManager manager;
    private final StudentModel studentModel;
    private final TeacherModel teacherModel;
    private final List<Person> people;
    private final List<Student> students;
    private final List<Teacher> teachers;

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
        students = manager.getAllStudents();
        teachers = manager.getAllTeachers();
        people = manager.getAllPeople();
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

    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void drag(MouseEvent event)
    {
        dragging(event, bp);
    }

    @FXML
    private void setOffset(MouseEvent event)
    {
        startDrag(event);
    }

    private void checkLoginInformation() throws IOException
    {
        String usernameInput = txtUser.getText();
        String passwordInput = txtPass.getText();
        checkUserInput(usernameInput, passwordInput);
    }

    private void checkUserInput(String userName, String password)
    {
        Stage primaryStage = (Stage) txtUser.getScene().getWindow();
        
        
        for (Person person : people)
        {
            if (userName.equals(person.getUserName()) && password.equals(person.getPassword()))
            {
                if (person instanceof Teacher)
                {
                    teacherModel.setCurrentUser((Teacher) person);
                    try
                    {
                        loadStage("/attendence/gui/view/TeacherView.fxml", "Teacher");
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                else if (person instanceof Student)
                {
                    studentModel.setCurrentUser((Student) person);
                    try
                    {
                        loadStage("/attendence/gui/view/StudentView.fxml", "Student");
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

//    private void checkIfTeacher(String usernameInput, String passwordInput) throws IOException
//    {
//        for (Teacher teacher : teachers)
//        {
//            if (usernameInput.equals(teacher.getUserName()) && passwordInput.equals(teacher.getPassword()))
//            {
//                teacherModel.setCurrentUser(teacher);
//                loadStage("/attendence/gui/view/TeacherView.fxml", "Teacher");
//            }
//            else if ()
//        }
//    }

    private void loadStage(String viewPath, String title) throws IOException
    {
        Stage primaryStage = (Stage) txtUser.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        Parent root = loader.load();
        primaryStage.close();
        
        Stage newStage = new Stage(StageStyle.UNDECORATED);
        newStage.setScene(new Scene(root));
        
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.setTitle(title);
        
        newStage.show();
    }

//    private void checkIfStudent(String usernameInput, String passwordInput) throws IOException
//    {
//        for (Student student : students)
//        {
//            if (usernameInput.equals(student.getUserName()) && passwordInput.equals(student.getPassword()))
//            {
//                studentModel.setCurrentUser(student);
//                Stage primaryStage = (Stage) txtUser.getScene().getWindow();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendence/gui/view/StudentView.fxml"));
//                Parent root = loader.load();
//                primaryStage.close();
//
//                Stage newStage = new Stage(StageStyle.UNDECORATED);
//                newStage.setScene(new Scene(root));
//
//                newStage.initModality(Modality.WINDOW_MODAL);
//                newStage.initOwner(primaryStage);
//                newStage.setTitle("Student");
//
//                newStage.show();
//
//            }
//        }
//    }
}
