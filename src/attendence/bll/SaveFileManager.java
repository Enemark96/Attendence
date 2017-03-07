/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.bll;

import attendence.be.Person;
import attendence.dal.SaveFileDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
public class SaveFileManager
{
    private SaveFileDAO saveFileDAO;

    public SaveFileManager()
    {
        saveFileDAO = new SaveFileDAO();
    }
    
    
    public void saveLoginData(Person person) throws IOException 
    {
        
            saveFileDAO.writeObjectData(person, "LogInData.dat");
  
    }
    
    public Person loadLoginData() throws FileNotFoundException 
    {
        
            return saveFileDAO.readObjectData("LogInData.dat");
 
    }

    
}
