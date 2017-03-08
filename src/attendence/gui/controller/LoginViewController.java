package attendence.gui.controller;

import attendence.be.Person;
import attendence.be.Student;
import attendence.be.Teacher;
import attendence.bll.PersonManager;
import attendence.gui.model.LoginModel;
import attendence.gui.model.StudentModel;
import attendence.gui.model.TeacherModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    Person loadedPerson;
    private final PersonManager manager;
    private final StudentModel studentModel;
    private final TeacherModel teacherModel;
    private LoginModel loginModel;
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
    @FXML
    private CheckBox checkBoxRemember;

    public LoginViewController() throws SQLException, IOException
    {
        this.studentModel = StudentModel.getInstance();
        this.teacherModel = TeacherModel.getInstance();
        loginModel = LoginModel.getInstance();
        this.manager = new PersonManager();
        students = manager.getAllStudents();
        teachers = manager.getAllTeachers();
        people = manager.getAllPeople();
        loadedPerson =  loginModel.loadLoginData();
     

      

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
             txtUser.setText(loadedPerson.getUserName());
            txtPass.setText(loadedPerson.getPassword());

    }

    @FXML
    private void handleLogin()
    {
        if (!"".equals(txtUser.getText()) && !"".equals(txtPass.getText()))
        {
            checkLoginInformation();
        } else
        {
            System.out.println("Udfyld venligst brugernavn og kodeord");
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

    private void checkLoginInformation()
    {
        String usernameInput = txtUser.getText();
        String passwordInput = txtPass.getText();
        try
        {
            checkUserInput(usernameInput, passwordInput);
        } catch (IOException ex)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("I/O Error");
            alert.setHeaderText("");
            alert.setContentText("The user information you typed can not be found"
                    + " in our database.");

            alert.showAndWait();
        }
    }

    private void checkUserInput(String userName, String password) throws IOException
    {
        for (Person person : people)
        {
            if (userName.equals(person.getUserName()) && password.equals(person.getPassword()))
            {
                if (person instanceof Teacher)
                {
                    teacherModel.setCurrentUser((Teacher) person);
                    if (checkBoxRemember.isSelected())
                    {
                        loginModel.saveLoginData(person);
                    }
                    loadStage("/attendence/gui/view/TeacherView.fxml", "Teacher");
                } else if (person instanceof Student)
                {
                    studentModel.setCurrentUser((Student) person);
                    if (checkBoxRemember.isSelected())
                    {
                        loginModel.saveLoginData(person);
                    }
                    loadStage("/attendence/gui/view/StudentView.fxml", "Student");
                }
            }
        }
    }

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

}
