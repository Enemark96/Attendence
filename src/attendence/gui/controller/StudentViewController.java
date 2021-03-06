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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    private Button btnCheckIn;
    @FXML
    private PieChart absenceChart;
    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<String> comboMonth;
    @FXML
    private Label labelProcent;
    @FXML
    private HBox calendarContainer;

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

        comboMonth.setItems(dateTimeModel.getFormattedMonths());

        updateChart();
        if (model.getCurrentUser().getLastCheckIn() != null)
        {
            if (model.getCurrentUser().getLastCheckOut() != null)
            {
                if (model.getCurrentUser().getLastCheckIn().compareTo(model.getCurrentUser().getLastCheckOut()) > 0)
                {
                    checkInStyle(false);
                }
            }
            else
            {
                checkInStyle(false);
            }
        }
    }

    @FXML
    private void handleCheckIn() throws SQLException
    {
        checkInStyle(checkedIn());
        checkInDataHandle(checkedIn());
    }

    private void checkInDataHandle(boolean checkedIn) throws SQLException
    {
        if (checkedIn)
        {
            model.getCurrentUser().setLastCheckIn(Timestamp.valueOf(LocalDateTime.now()));
            manager.updateCheckIn(model.getCurrentUser());
        }
        else
        {
            model.getCurrentUser().setLastCheckOut(Timestamp.valueOf(LocalDateTime.now()));
            manager.updateCheckOut(model.getCurrentUser());
        }
    }

    private void checkInStyle(boolean checkedIn)
    {
        String btnText;
        String btnStyle;
        String loginText = "";

        if (checkedIn)
        {
            btnText = "Check-in";
            btnStyle = "-fx-background-color : LIGHTGREEN;";
        }
        else
        {
            btnText = "Check-out";
            btnStyle = "-fx-background-color : #FF0033;";
            loginText = ", you are now cheked-in";
        }
        btnCheckIn.setText(btnText);
        btnCheckIn.setStyle(btnStyle);
        lblUser.setText(model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName() + loginText);
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
