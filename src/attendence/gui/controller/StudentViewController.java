/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import attendence.be.Absence;
import attendence.bll.PersonManager;
import attendence.gui.model.DateTimeModel;
import attendence.gui.model.StudentModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class StudentViewController extends Dragable implements Initializable
{

    private final ObservableList data;
    private final StudentModel model;
    private final DateTimeModel dateTimeModel;
    private final PersonManager manager;
    private final List<Absence> absences;

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
    @FXML
    private Label labelProcent;

    public StudentViewController() throws SQLException, IOException
    {
        this.manager = new PersonManager();
        this.data = FXCollections.observableArrayList();
        this.absences = manager.getAllAbsence();
        this.model = StudentModel.getInstance();
        this.dateTimeModel = new DateTimeModel();

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

        comboMonth.setItems(dateTimeModel.getFormattedMonths());

        updateChart();
    }

    @FXML
    private void handleCheckIn() throws SQLException
    {
        if (checkedIn())
        {
            btnCheckIn.setText("Check-in");
            btnCheckIn.setStyle("-fx-background-color : LIGHTGREEN;");
            lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());
        } else
        {
            btnCheckIn.setText("Check-out");
            btnCheckIn.setStyle("-fx-background-color : #FF0033;");
            lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName() + ", you are now cheked-in");
            model.getCurrentUser().setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
            manager.updatecheckIn(model.getCurrentUser());
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
        dragging(event, lblUser);
    }

    @FXML
    private void setOffset(MouseEvent event)
    {
        startDrag(event);
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

        absenceChart.setLabelLineLength(100);
        absenceChart.setLegendSide(Side.LEFT);

        labelProcent.setTextFill(Color.DARKORANGE);
        labelProcent.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : absenceChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,
                    new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent e)
                {
                    labelProcent.setTranslateX(e.getSceneX() - 180);
                    labelProcent.setTranslateY(e.getSceneY() - 25);

                    labelProcent.setText(String.valueOf(data.getPieValue()) + "%");

                }
            });

        }

    }
}