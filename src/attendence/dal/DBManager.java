/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.dal;

import attendence.be.Student;
import attendence.be.Teacher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class DBManager {

    ConnectionManager cm;
    
    List<Student> students;
    List<Teacher> teachers;

    public DBManager() throws SQLException
    {
        setAllPeople();
    }

    
    
    
    public void setAllPeople() throws SQLException
    {
//        String sql = "SELECT ID, FirstName, LastName, UNILogin, Password, IsStudent, Class FROM People";
        String sql = "SELECT * FROM People";

        try (Connection con = cm.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt(1);
                    String fName = rs.getString(2);
                    String lName = rs.getString(3);
                    String user = rs.getString(4);
                    String pass = rs.getString(5);
                if (rs.getBoolean("IsStudent"))
                {
                    
                    String cName = rs.getString(6);

                    Student student = new Student(id, fName, lName, user, pass, cName);
                    students.add(student);
                }else{
                    Teacher teacher = new Teacher(id, fName, lName, user, pass);
                    teachers.add(teacher);
                }
            }
        }

    }

    public List<Student> getStudents()
    {
        return students;
    }

    public List<Teacher> getTeachers()
    {
        return teachers;
    }
    
    
}
