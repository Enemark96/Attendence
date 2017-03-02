/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.model;

import attendence.be.Absence;
import attendence.be.Student;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author James
 */
public class StudentModel
{

    private Student currentUser;
    private List<Absence> missedClasses;

    private static StudentModel instance;

    public static StudentModel getInstance()
    {
        if (instance == null)
        {
            instance = new StudentModel();
        }
        return instance;
    }

    private StudentModel()
    {
        this.missedClasses = FXCollections.observableArrayList();

    }

    public List<Absence> getMissedClasses()
    {
        return missedClasses;
    }

    public ObservableList<String> getMissedClassesAsString()
    {

        ObservableList<String> returnList = FXCollections.observableArrayList();
        for (Absence missedClass : missedClasses)
        {
            String date = missedClass.getDate() + "";
            date = date.substring(0, 10);
            returnList.add(date);
        }
        return returnList;
    }

    public void setMissedClasses(ObservableList missedClasses)
    {
        this.missedClasses = missedClasses;
    }

    public void addMissedClass(Absence miss)
    {
        missedClasses.add(miss);
    }

    public Student getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(Student currentUser)
    {
        this.currentUser = currentUser;
    }

}
