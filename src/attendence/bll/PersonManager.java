package attendence.bll;

import attendence.be.Absence;
import attendence.be.Person;
import attendence.be.Student;
import attendence.be.Teacher;
import attendence.dal.DBManager;
import attendence.dal.PersonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * A class that holds data about the people.
 * @author Simon Birkedal, Stephan Fuhlendorff, Thomas Hansen & Jacob Enemark
 */
public class PersonManager
{

    PersonDAO personDAO = PersonDAO.getInstance();
    DBManager dbManager;

    public PersonManager() throws SQLException, IOException
    {
        this.dbManager = new DBManager();
    }

    /**
     * Gets all the students.
     * @return 
     */
    public List<Student> getAllStudents()
    {
        return dbManager.getStudents();
    }

    /**
     * Gets all the teachers.
     * @return 
     */
    public List<Teacher> getAllTeachers()
    {
        return dbManager.getTeachers();
    }

    /**
     * Gets the absence for each person.
     * @return 
     */
    public List<Absence> getAllAbsence()
    {
        return personDAO.getAllAbsence();
    }

    /**
     * Gets all the people (Teachers and students)
     * @return 
     */
    public List<Person> getAllPeople()
    {
        return dbManager.getPeople();
    }
}
