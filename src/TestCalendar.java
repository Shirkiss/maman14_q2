import javax.swing.*;

/**
 * TestCalendar.java
 * Purpose: Create a calender app and let the user save and load reminder on it
 *
 * @author Shir Cohen
 */
public class TestCalendar {

    public static void main(String[] args) {
        CalendarFrame calendarFrame = new CalendarFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setSize(500, 500);
        calendarFrame.setVisible(true);
    }
}