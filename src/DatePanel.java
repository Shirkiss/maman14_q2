import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

/**
 * Created by shir.cohen on 12/30/2017.
 */
public class DatePanel extends JPanel{

    public JComboBox<Integer> comboxBoxDay;
    public JComboBox<Integer> comboxBoxYear;
    public JComboBox<String> comboxBoxMonth;
    public DatePanel() {
        Integer[] days = new Integer[31];
        for (int i = 0; i < days.length; i++)
            days[i] = i + 1;
        this.comboxBoxDay = new JComboBox<>(days);

        String[] months = {"January", "January", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        this.comboxBoxMonth = new JComboBox<>(months);
        Integer[] year = new Integer[60];
        for (int i = 0; i < year.length; i++)
            year[i] = i + 1970;
        this.comboxBoxYear = new JComboBox<>(year);

        add(comboxBoxDay);
        add(comboxBoxMonth);
        add(comboxBoxYear);
    }


}
