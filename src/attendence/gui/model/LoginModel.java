/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.gui.model;

import attendence.be.Person;
import attendence.bll.SaveFileManager;
import attendence.dal.SaveFileDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author thomas
 */
public class LoginModel
{
    private SaveFileManager saveFileManager;
    private SaveFileDAO saveFileDAO;
    

    
    private static LoginModel instance;
        public static LoginModel getInstance()
    {
        if (instance == null)
        {
            instance = new LoginModel();
        }
        return instance;
    }

    private LoginModel()
    {
        saveFileManager = new SaveFileManager();
        saveFileDAO = new SaveFileDAO();

    }
    
        public Person loadLoginData() throws FileNotFoundException 
    {
       return  saveFileManager.loadLoginData();

    }

    public void saveLoginData(Person person) throws IOException
    {
        saveFileManager.saveLoginData(person);
        
    }
        
}
