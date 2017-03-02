package attendence.bll;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * 
 * @author Simon Birkedal, Stephan Fuhlendorff, Thomas Hansen & Jacob Enemark
 */
public class DateTimeManager
{
    private final ArrayList<String> capitalizedMonths;
    
    public DateTimeManager()
    {
        this.capitalizedMonths = new ArrayList<>();
    }
    
    public String getCurrentMonth()
    {
        String monthOfYear = LocalDateTime.now().toLocalDate().getMonth().name().toLowerCase();
        String monthCapitalized = capitalize(monthOfYear);
        return monthCapitalized;
    }
    
    public int getCurrentDayOfMonth()
    {
        int dayOfMonth = LocalDateTime.now().toLocalDate().getDayOfMonth();
        return dayOfMonth;
    }
    
    public ArrayList<String> getMonthsCapitalized()
    {        
        for (Month month : Month.values())
        {
            String monthName = month.name().toLowerCase();
            String upperMonth = this.capitalize(monthName);
            this.capitalizedMonths.add(upperMonth);
        }
        
        return this.capitalizedMonths;
    }
    
    /**
     * Converts the first character in a literal String to upper case.
     *
     * @param line The string to be converted.
     * @return Returns a new String with the first character in the sequence as
     * capitalized. (Upper case)
     */
    private String capitalize(String line)
    {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
