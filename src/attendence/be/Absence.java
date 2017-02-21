/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.be;

import java.util.Date;

/**
 *
 * @author James
 */
public class Absence {

    int id;
    Date date;
    int studentId;

    public Absence(int id, Date date, int studentId)
    {
        this.id = id;
        this.date = date;
        this.studentId = studentId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    
}
