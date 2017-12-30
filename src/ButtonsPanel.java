import javax.swing.*;
import java.util.Hashtable;

/**
 * Created by shir.cohen on 12/30/2017.
 */
class ButtonsPanel extends JPanel {
    ButtonsPanel(Hashtable calender, JTextArea comment, DatePanel datePanel) {
        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(
                e -> {
                    MyDate myDate = new MyDate(Integer.parseInt(datePanel.comboxBoxYear.getSelectedItem().toString()),
                            (datePanel.comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(datePanel.comboxBoxDay.getSelectedItem().toString()));
                    calender.put(myDate, comment.getText());
                    System.out.println(calender.get(myDate));
                }
        );
        JButton loadButton = new JButton("Load");
        add(loadButton);
        loadButton.addActionListener(
                e -> {
                    MyDate myDate = new MyDate(Integer.parseInt(datePanel.comboxBoxYear.getSelectedItem().toString()),
                            (datePanel.comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(datePanel.comboxBoxDay.getSelectedItem().toString()));
                    try {
                        comment.setText(calender.get(myDate).toString());
                    } catch (NullPointerException ex) {
                        comment.setText("You haven't enter anything yet for this date. Enter you comment here");
                    }
                }
        );
    }

}
