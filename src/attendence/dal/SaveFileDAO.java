/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.dal;

import attendence.be.Person;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author thomas
 */
public class SaveFileDAO
{
      public void writeObjectData( Person person, String fileName) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(person);
        }
    }

    /**
     * Reads an object from a file, the object must be an arraylist of playlists.
     * @param fileName The fileName to read the information from.
     * @return Returns a new array containing all the stored data.
     * @throws FileNotFoundException 
     */
    public Person readObjectData(String fileName) throws FileNotFoundException
    {
        Person person = null;

        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try (ObjectInputStream ois = new ObjectInputStream(bis))
        {
            
              person =  (Person) ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            // Handle exception
        }
        return person;
    }
}
