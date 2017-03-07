package attendence.be;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Jacob Enemark
 */
public class Student extends Person {

    private String className;
    private int totalAbsence;
    private Timestamp lastLogin;

    /**
     * The default constructor for the student class.
     *
     * @param id The id of the student.
     * @param firstName The student's first name.
     * @param lastName The student's last name.
     * @param username The student's user name.
     * @param password The password matching the student's user name.
     * @param className The student's class name, that is the name of the class
     * @param lastLogin in which the student participates. e.g. A
     */
    public Student(int id, String firstName, String lastName, String email, String username, String password, String phoneNum, String className, Timestamp lastLogin)
    {
        super(id, firstName, lastName, email, username, password, phoneNum);
        this.className = className;
        this.lastLogin = lastLogin;
    }

    /**
     * Gets the class name of the student.
     *
     * @return
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * Sets the class name of the student.
     *
     * @param className The class name.
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * Sets the student's total absence.
     *
     * @param totalAbsence The total absence.
     */
    public void setTotalAbsence(int totalAbsence)
    {
        this.totalAbsence = totalAbsence;
    }

    /**
     * Gets the student's total absence.
     *
     * @return Returns the student's total absence represented by an integer
     * value.
     */
    public int getTotalAbsence()
    {
        return totalAbsence;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin)
    {
        this.lastLogin = lastLogin;
    }
    
    

}
