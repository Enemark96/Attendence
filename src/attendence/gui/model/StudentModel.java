/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.model;

import attendence.be.Absence;
import attendence.be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author James
 */
public class StudentModel {
    Student currentUser;
    ObservableList<Absence> missedClasses;

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

    public ObservableList<Absence> getMissedClasses()
    {
        return missedClasses;
    }

    public void setMissedClasses(ObservableList missedClasses)
    {
        this.missedClasses = missedClasses;
    }
    
    public void addMissedClass(Absence miss){
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
