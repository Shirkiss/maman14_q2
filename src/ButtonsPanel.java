import javax.swing.*;
import java.util.HashMap;

/**
 * Created by shir.cohen on 12/30/2017.
 */
class ButtonsPanel extends JPanel {

    ButtonsPanel(HashMap calender, JTextArea comment, DatePanel datePanel) {
        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(
                e -> {
                    CalenderDate calenderDate = new CalenderDate(Integer.parseInt(datePanel.comboxBoxYear.getSelectedItem().toString()),
                            (datePanel.comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(datePanel.comboxBoxDay.getSelectedItem().toString()));
                    calender.put(calenderDate, comment.getText());
                    System.out.println(calender.get(calenderDate));
                }
        );
        JButton loadButton = new JButton("Load");
        add(loadButton);
        loadButton.addActionListener(
                e -> {
                    CalenderDate calenderDate = new CalenderDate(Integer.parseInt(datePanel.comboxBoxYear.getSelectedItem().toString()),
                            (datePanel.comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(datePanel.comboxBoxDay.getSelectedItem().toString()));
                    try {
                        comment.setText(calender.get(calenderDate).toString());
                    } catch (NullPointerException ex) {
                        comment.setText("You haven't enter anything yet for this date. Enter you comment here");
                    }
                }
        );
    }

}
