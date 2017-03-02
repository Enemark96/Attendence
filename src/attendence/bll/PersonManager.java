package attendence.bll;

import attendence.be.Absence;
import attendence.be.Person;
import attendence.be.Student;
import attendence.be.Teacher;
import attendence.dal.PersonDAO;
import java.util.List;

/**
 * A class that holds data about the people.
 * @author Simon Birkedal, Stephan Fuhlendorff, Thomas Hansen & Jacob Enemark
 */
public class PersonManager
{

    PersonDAO personDAO = PersonDAO.getInstance();

    /**
     * Gets all the students.
     * @return 
     */
    public List<Student> getAllStudents()
    {
        return personDAO.getAllStudents();
    }

    /**
     * Gets all the teachers.
     * @return 
     */
    public List<Teacher> getAllTeachers()
    {
        return personDAO.getAllTeachers();
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
        return personDAO.getAllPeople();
    }
}
