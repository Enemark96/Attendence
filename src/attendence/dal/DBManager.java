/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendence.dal;

import attendence.be.Student;
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

    public List<Student> getAllStudents() throws SQLException
    {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT ID, FirstName, LastName, UNILogin, Password, IsStudent, Class FROM People";

        try (Connection con = cm.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.getResultSet();

            while (rs.next())
            {
                if (rs.getBoolean("IsStudent"))
                {
                    int id = rs.getInt(1);
                    String fName = rs.getString(2);
                    String lName = rs.getString(3);
                    String user = rs.getString(4);
                    String pass = rs.getString(5);
                    String cName = rs.getString(6);

                    Student student = new Student(id, fName, lName, user, pass, cName);
                    students.add(student);
                }
            }
        }

        return students;
    }
}
