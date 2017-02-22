/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.be;

/**
 *
 * @author Jacob Enemark
 */
public class Student extends Person {

    String className;
    int amountOfAbsence;

    public Student(int id, String firstName, String lastName, String username, String password, String className)
    {
        super(id, firstName, lastName, username, password);
        this.className = className;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public void setAmountOfAbsence(int amountOfAbsence)
    {
        this.amountOfAbsence = amountOfAbsence;
    }

    public int getAmountOfAbsence()
    {
        return amountOfAbsence;
    }
    
    

}
