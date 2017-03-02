package attendence.bll;

import attendence.be.Absence;
import attendence.be.Person;
import attendence.be.Student;
import attendence.be.Teacher;
import attendence.dal.PersonDAO;
import java.util.List;

/**
 *
 * @author Simon Birkedal, Stephan Fuhlendorff, Thomas Hansen & Jacob Enemark
 */
public class PersonManager
{

    PersonDAO personDAO = PersonDAO.getInstance();

    public List<Student> getAllStudents()
    {
        return personDAO.getAllStudents();
    }

    public List<Teacher> getAllTeachers()
    {
        return personDAO.getAllTeachers();
    }

    public List<Absence> getAllAbsence()
    {
        return personDAO.getAllAbsence();
    }

    public List<Person> getAllPeople()
    {
        return personDAO.getAllPeople();
    }
}
