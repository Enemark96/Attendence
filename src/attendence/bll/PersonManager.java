/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.bll;

import attendence.be.Student;
import attendence.dal.PersonDAO;
import java.util.List;

/**
 *
 * @author Jacob Enemark
 */
public class PersonManager {
    
    PersonDAO personDAO = PersonDAO.getInstance();
    

    public List getAllStudents()
    {
        return personDAO.getAllStudents();
    }
    
     public List getAllTeachers()
    {
        return personDAO.getAllTeachers();
    }
     
}
