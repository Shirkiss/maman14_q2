import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * Created by shir.cohen on 12/30/2017.
 */
class CalenderFrame extends JFrame {
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private HashMap<String, String> calender = new HashMap<>();
    private JTextArea textArea;
    private JComboBox<Integer> comboxBoxDay;
    private JComboBox<String> comboxBoxMonth;
    private JComboBox<Integer> comboxBoxYear;
    
    CalenderFrame() {
        super("Calender Notes");
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        JMenuBar menu = buildMenuBar();
        setJMenuBar(menu);

        JPanel datePanel = buildDatePanel();
        datePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Date"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(datePanel, 0, 0, 1, 1);

        textArea = new JTextArea("Please write down a comment");
        textArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Comment"));
        constraints.weightx = 1;
        constraints.weighty = 100;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(textArea, 1, 0, 1, 1);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0;
        JPanel buttonsPanel = buildButtonsPanel();
        addComponent(buttonsPanel, 2, 0, 1, 1);
    }

    private void addComponent(Component component, int row, int column, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }

    private JPanel buildButtonsPanel() {
        JPanel panel = new JPanel();
        JButton saveButton = new JButton("Save");
        panel.add(saveButton);
        saveButton.addActionListener(
                e -> {
                    MyDate myDate = new MyDate(Integer.parseInt(comboxBoxYear.getSelectedItem().toString()),
                            (comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(comboxBoxDay.getSelectedItem().toString()));
                    calender.put(myDate.toString(), textArea.getText());
                    JOptionPane.showMessageDialog(null, "Your reminder was saved!\n " + textArea.getText()
                            , myDate.toString(), JOptionPane.PLAIN_MESSAGE);
                }
        );
        JButton loadButton = new JButton("Load");
        panel.add(loadButton);
        loadButton.addActionListener(
                e -> {
                    MyDate myDate = new MyDate(Integer.parseInt(comboxBoxYear.getSelectedItem().toString()),
                            (comboxBoxMonth.getSelectedIndex() + 1),
                            Integer.parseInt(comboxBoxDay.getSelectedItem().toString()));
                    try {
                        textArea.setText(calender.get(myDate));
                    } catch (NullPointerException ex) {
                        textArea.setText("You haven't enter anything yet for this date. Enter you comment here");
                    }
                }
        );
        return panel;
    }

    private JPanel buildDatePanel() {
        JPanel panel = new JPanel();
        Integer[] days = new Integer[31];
        for (int i = 0; i < days.length; i++)
            days[i] = i + 1;
        comboxBoxDay = new JComboBox<>(days);

        String[] months = {"January", "January", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        comboxBoxMonth = new JComboBox<>(months);
        Integer[] year = new Integer[60];
        for (int i = 0; i < year.length; i++)
            year[i] = i + 1970;
        comboxBoxYear = new JComboBox<>(year);

        panel.add(comboxBoxDay);
        panel.add(comboxBoxMonth);
        panel.add(comboxBoxYear);
        return panel;
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //create file menu
        fileMenu.setMnemonic('F');

        JMenuItem newItem = new JMenuItem("New");
        newItem.setMnemonic('N');
        fileMenu.add(newItem); // add save to file menu
        newItem.addActionListener(
                e -> {
                    textArea.setText("");
                    calender = new HashMap<>();
                }
        );
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setMnemonic('S');
        fileMenu.add(saveItem); // add save to file menu
        saveItem.addActionListener(
                e -> saveToFile()
        );
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setMnemonic('O');
        fileMenu.add(openItem); // add save to file menu
        openItem.addActionListener(
                e -> {
                    File file = getFilePath();
                    if (file != null && Files.exists(file.toPath())) {
                        try {
                            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
                            calender = (HashMap<String, String>) ois.readObject();
                        } catch (IOException | ClassNotFoundException ioException) {
                            System.err.println("Error opening file");
                        }
                    }
                }
        );
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem); // add exit to file menu
        exitItem.addActionListener(
                e -> System.exit(0)
        );

        bar.add(fileMenu);
        return bar;
    }

    private File getFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION)
            return null;
        return fileChooser.getSelectedFile();
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            if (!file.getName().toLowerCase().endsWith(".ser")) {
                file = new File(file.getParentFile(), file.getName() + ".ser");
            }
            try {
                FileOutputStream fout = new FileOutputStream(file.getName());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(calender);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}