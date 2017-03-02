package attendence.be;

import java.util.Date;

/**
 * This class holds all the information about a students absence.
 * @author James
 */
public class Absence {

    private int id;
    private Date date;
    private int studentId;
    
    
    /**
     * The default constructor for the absence class.
     * @param id The id of the students absence.
     * @param date The date of which the students absence occured.
     * @param studentId The id of the student.
     */
    public Absence(int id, Date date, int studentId)
    {
        this.id = id;
        this.date = date;
        this.studentId = studentId;
    }

    /**
     * Gets the absence id.
     * @return Returns the absence id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the absence id.
     * @param id The absence id to be set.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * The date the absence occured.
     * @return The date the absence occured.
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets a date on which the absence occured.
     * @param date The date to set the absence for.
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Gets the student id of the person being away.
     * @return Returns the id of the absenct person.
     */
    public int getStudentId()
    {
        return studentId;
    }

    /**
     * Sets a student id for the absent person.
     * @param studentId The id to be set to being absent.
     */
    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    
}
