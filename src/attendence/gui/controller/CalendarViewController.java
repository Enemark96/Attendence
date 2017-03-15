/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author James
 */
public class CalendarViewController implements Initializable
{

    private Calendar cal;
    private int year;
    private int month;
//    private int day;
    private final String todayStyle;
    private ObservableList<String> months;
    private ObservableList<Integer> years;
    @FXML
    private GridPane gridCalendar;
    @FXML
    private ComboBox<String> cmbMonth;
    @FXML
    private ComboBox<Integer> cmbYear;

    public CalendarViewController()
    {
        this.todayStyle = "-fx-border-color: red;";
        this.cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
//        day = cal.get(Calendar.DATE);
        months = FXCollections.observableArrayList(
                "January",
                "Febuary",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );
        years = FXCollections.observableArrayList(
                year,
                year - 2,
                year - 3,
                year - 4,
                year - 5
        );
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillCalendar();
        cmbMonth.setItems(months);
        cmbMonth.getSelectionModel().select(Calendar.MONTH);
        cmbYear.setItems(years);
        cmbYear.getSelectionModel().select(0);
    }

    private void fillCalendar()
    {
        gridCalendar.getChildren().remove(5, gridCalendar.getChildren().size()); // Clears everything except the weekdays
        cal.set(year, month, 1); // Sets the month/year for the calendar to show
        String dayOfWeek = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH); // Gets the shortened name for the day
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // Gets number of days in the selected month
        int gridX = 0;
        int gridY = 1;
        switch (dayOfWeek)
        {
            case "Mon":
                gridX = 0;
                break;
            case "Tue":
                gridX = 1;
                break;
            case "Wed":
                gridX = 2;
                break;
            case "Thu":
                gridX = 3;
                break;
            case "Fri":
                gridX = 4;
                break;
            case "Sat":
                gridX = 5;
                break;
            case "Sun":
                gridX = 6;
                break;
            default:
                gridX = 0;
                break;
        }

        // Loops through the grid placing numbers equal to the amount of days in the month
        for (int i = 1; i < daysInMonth + 1; i++)
        {
            if (gridX <= 4)
            {
                Button btn = new Button(i + "");
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                Calendar tempCal = Calendar.getInstance();
                if (i == tempCal.get(Calendar.DATE) && month == tempCal.get(Calendar.MONTH) && year == tempCal.get(Calendar.YEAR))
                {
                    btn.setStyle(todayStyle);
                }
                gridCalendar.add(btn, gridX, gridY);
            }

            // Next row and back to the first column after sunday
            if (gridX == 6)
            {
                gridX = 0;
                gridY++;
            }
            else
            {
                gridX++;
            }
        }
    }

    @FXML
    private void changeYear(ActionEvent event)
    {
        year = cmbYear.getValue();
        fillCalendar();

//        String input = txtYear.getText();
//        int yearInput = year; // defaults the variable
//        try
//        {
//            yearInput = Integer.parseInt(input); // checks if only numbers are present
//
//        }
//        catch (NumberFormatException e)
//        {
//            System.out.println("Not a valid number"); // if other than numbers are present
//        }
//        int length = String.valueOf(yearInput).length(); // Get length of input
//        if (length == 4)
//        {
//            year = yearInput;
//            fillCalendar();
//        }
//        else
//        {
//            System.out.println("Year has to be 4 digits");
//        }
    }

    @FXML
    private void changeMonth(ActionEvent event)
    {
        String selected = cmbMonth.getValue();
        for (int i = 0; i < months.size(); i++)
        {
            if (selected.equals(months.get(i)))
            {
                month = i;
            }
        }
        fillCalendar();
    }
}
