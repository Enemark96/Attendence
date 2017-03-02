package attendence.gui.model;

import attendence.be.Teacher;

/**
 *
 * @author James
 */
public class TeacherModel
{

    private Teacher currentUser;

    private static TeacherModel instance;

    public static TeacherModel getInstance()
    {
        if (instance == null)
        {
            instance = new TeacherModel();
        }
        return instance;
    }

    private TeacherModel()
    {

    }

    public Teacher getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(Teacher currentUser)
    {
        this.currentUser = currentUser;
    }

}
