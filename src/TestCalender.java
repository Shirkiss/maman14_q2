import javax.swing.*;

/**
 * TestCalender.java
 * Purpose: Create a calender app and let the user save and load reminder on it
 *
 * @author Shir Cohen
 */
public class TestCalender {

    public static void main(String[] args) {
        CalenderFrame calenderFrame = new CalenderFrame();
        calenderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calenderFrame.setSize(500, 500);
        calenderFrame.setVisible(true);
    }
}