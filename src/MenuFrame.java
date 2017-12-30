import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shir.cohen on 12/30/2017.
 */
public class MenuFrame extends JMenuBar {
    public MenuFrame() {
        JMenu fileMenu = new JMenu("File"); //create file menu
        fileMenu.setMnemonic('F');

        JMenuItem saveItam = new JMenuItem("Save");
        saveItam.setMnemonic('S');
        fileMenu.add(saveItam); // add save to file menu
        saveItam.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setMnemonic('L');
        fileMenu.add(loadItem); // add save to file menu
        loadItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem); // add save to file menu
        exitItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        add(fileMenu);
    }
}
