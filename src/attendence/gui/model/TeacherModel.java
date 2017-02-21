/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.model;

import attendence.be.Teacher;

/**
 *
 * @author James
 */
public class TeacherModel {
    Teacher currentUser;

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
