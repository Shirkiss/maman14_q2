import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created by shir.cohen on 12/30/2017.
 */
public class CalenderFrame extends JFrame {
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;

    public CalenderFrame(){
        super("Calender Notes");
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        JMenuBar menu = new MenuFrame();
        setJMenuBar(menu);

        DatePanel datePanel = new DatePanel();
        datePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Date"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(datePanel,0,0,1,1);

        JTextArea textArea = new JTextArea("Please write down a comment");
        textArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Comment"));
        constraints.weightx = 1;
        constraints.weighty = 100;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(textArea,1,0,1,1);

        Hashtable calender = new Hashtable();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0;
        ButtonsPanel buttonsPanel = new ButtonsPanel(calender, textArea, datePanel);
        addComponent(buttonsPanel,2,0,1,1);


    }

    private void addComponent(Component component, int row, int column, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }
}
