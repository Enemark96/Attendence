package attendence.gui.controller;

import attendence.be.Person;
import attendence.be.Student;
import attendence.be.Teacher;
import attendence.bll.PersonManager;
import attendence.gui.model.StudentModel;
import attendence.gui.model.TeacherModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class LoginViewController extends Dragable implements Initializable {

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

    public LoginViewController() throws SQLException, IOException
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
        try
        {
            checkLoginInformation(txtUser.getText(), txtPass.getText());
        }
        catch (IOException ex)
        {
            showErrorDialog("I/O Error", "", "We couldn't get access to the "
                    + "requested data!");
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
    
//    private Person getUser(String userName, String password)
//    {
//        for (Person person : people)
//        {
//            if (userName.matches(person.getUserName()) && password.matches(person.getPassword()))
//            {
//                if (person instanceof Teacher || person instanceof Student)
//                {
//                    return person;
//                }
//            }
//        }
//        
//        return null;
//    }
    
//    private void checkLoginInformation(String userName, String password)
//    {      
//        if (getUser(userName, password) instanceof Teacher)
//        {
//            System.out.println("I'm a teacher");
//        }
//        else if (getUser(userName, password) instanceof Student)
//        {
//            System.out.println("I'm a student");
//        }
//        else
//        {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("I/O Error");
//            alert.setHeaderText("");
//            alert.setContentText("Either the username or the password you provided"
//                    + " could not be found in our database.");
//
//            alert.showAndWait();
//        }
//    }

    private void checkLoginInformation(String userName, String password) throws IOException
    {
        String userType = "";
        for (Person person : people)
        {
            if (userName.matches(person.getUserName()) && password.matches(person.getPassword()))
            {
                if (person instanceof Teacher)
                {
                    userType = "Teacher";
                    teacherModel.setCurrentUser((Teacher) person);
                }

                else if (person instanceof Student)
                {
                    userType = "Student";
                    studentModel.setCurrentUser((Student) person);                    
                }
                
                loadStage("/attendence/gui/view/" + userType + "View.fxml", userType);
                return;
            }
        }

        showErrorDialog("Login Error", "User not found", "Either the username or the password you provided"
                + " could not be found in our database.");
    }

    private void showErrorDialog(String title, String header, String content)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
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
