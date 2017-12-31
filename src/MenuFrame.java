import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by shir.cohen on 12/30/2017.
 */
public class MenuFrame extends JMenuBar {
    FileOutputStream fout = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    JMenuItem saveItem;
    JMenuItem openItem;

    public MenuFrame(HashMap<String,String> calender) {
        JMenu fileMenu = new JMenu("File"); //create file menu
        fileMenu.setMnemonic('F');

        saveItem = new JMenuItem("Save");
        saveItem.setMnemonic('S');
        fileMenu.add(saveItem); // add save to file menu

        openItem = new JMenuItem("Open");
        openItem.setMnemonic('O');
        fileMenu.add(openItem); // add save to file menu
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem); // add save to file menu
        exitItem.addActionListener(
                e -> System.exit(0)
        );

        add(fileMenu);
        ButtonHandler handler = new ButtonHandler(calender);
        saveItem.addActionListener(handler);
        openItem.addActionListener(handler);
    }

    private Path getFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION)
            return null;
        return fileChooser.getSelectedFile().toPath();
    }

    private class ButtonHandler implements ActionListener {
        private HashMap<String,String> calender_copy;

        public ButtonHandler(HashMap<String,String> calender){
            this.calender_copy = calender;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveItem) {
                try {
                    fout = new FileOutputStream("t.ser");
                    oos = new ObjectOutputStream(fout);
                    oos.writeObject(calender_copy);
                    System.out.println("Done");
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == openItem) {
                Path path = getFilePath();
                if (path != null && Files.exists(path)) {
                    try {
                        ois = new ObjectInputStream(Files.newInputStream(path));
                        calender_copy = (HashMap<String, String>) ois.readObject();
                    } catch (IOException | ClassNotFoundException ioException) {
                        System.err.println("Error opening file");
                    }
                }
            }
        }
    }
}
