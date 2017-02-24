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
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class StudentViewController extends Dragable implements Initializable {

    private ObservableList data;
    StudentModel model;
    PersonManager manager = new PersonManager();
    List<Absence> absences;

    @FXML
    private Label lblUser;
    @FXML
    private ListView<String> listMissedClasses;
    @FXML
    private Button btnCheckIn;
    @FXML
    private PieChart absenceChart;
    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<String> comboMonth;

    public StudentViewController()
    {
        this.data = FXCollections.observableArrayList();
        this.absences = manager.getAllAbsence();
        this.model = StudentModel.getInstance();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        for (Absence absence : absences)
        {
            if (model.getCurrentUser().getId() == absence.getStudentId())
            {
                model.addMissedClass(absence);
            }
        }
        lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());
        listMissedClasses.setItems(model.getMissedClassesAsString());


        for (Month month : Month.values())
        {
            comboMonth.getItems().add(month.name().toLowerCase());
        }


        updateChart();
    }

    @FXML
    private void handleCheckIn()
    {
        if (checkedIn())
        {
            btnCheckIn.setText("Check-in");
            btnCheckIn.setStyle("-fx-background-color : LIGHTGREEN;");
            lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());
        }
        else
        {
            btnCheckIn.setText("Check-out");
            btnCheckIn.setStyle("-fx-background-color : #FF0033;");
            lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName() + ", you are now cheked-in");
        }
    }

    private boolean checkedIn()
    {
        return "Check-out".equals(btnCheckIn.getText());
    }

    private void updateChart()
    {

        data.add(new PieChart.Data("ITO", 10));
        data.add(new PieChart.Data("SDE", 5));
        data.add(new PieChart.Data("SCO", 10));
        data.add(new PieChart.Data("DB", 10));
        data.add(new PieChart.Data("Attendence", 65));
        absenceChart.setData(data);

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
        dragging(event, lblUser);
    }

    @FXML
    private void setOffset(MouseEvent event)
    {
        startDrag(event);
    }
}
