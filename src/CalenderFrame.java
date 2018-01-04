import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * CalenderFrame.java
 * Purpose: Create a Frame with calender's reminder component - date picker, comment section and a menu.
 *
 * @author Shir Cohen
 */
class CalenderFrame extends JFrame {
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private HashMap<CalenderDate, String> calender = new HashMap<>();
    private JTextArea textArea;
    private JComboBox<Integer> comboxBoxDay;
    private JComboBox<String> comboxBoxMonth;
    private JComboBox<Integer> comboxBoxYear;

    CalenderFrame() {
        super("Calender Notes");
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        // create the menu bar
        JMenuBar menu = buildMenuBar();
        setJMenuBar(menu);

        // create the date section
        JPanel datePanel = buildDatePanel();
        datePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Date"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(datePanel, 0, 0, 1, 1);

        // create the comment section
        textArea = new JTextArea("Please write down a comment");
        textArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Comment"));
        constraints.weightx = 1;
        constraints.weighty = 100;
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(textArea, 1, 0, 1, 1);

        // create the buttons section
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0;
        JPanel buttonsPanel = buildButtonsPanel();
        addComponent(buttonsPanel, 2, 0, 1, 1);
    }

    /**
     * Add component to the Grid Bag Layout
     *
     * @param row
     * @param column
     * @param width
     * @param height
     */
    private void addComponent(Component component, int row, int column, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }

    /**
     * Create buttons panel with save and load comments buttons
     *
     * @return JPanel
     */
    private JPanel buildButtonsPanel() {
        JPanel panel = new JPanel();
        JButton saveButton = new JButton("Save");
        panel.add(saveButton);
        // ActionListener when clicking on the save buttons
        saveButton.addActionListener(
                e -> {
                    try {
                        // create CalenderDate object with the selected date
                        CalenderDate calenderDate = new CalenderDate(Integer.parseInt(comboxBoxYear.getSelectedItem().toString()),
                                (comboxBoxMonth.getSelectedIndex() + 1),
                                Integer.parseInt(comboxBoxDay.getSelectedItem().toString()));
                        // store this date and comment on the calender HashMap and show the user a message
                        calender.put(calenderDate, textArea.getText());
                        JOptionPane.showMessageDialog(null, "Your reminder was saved!\n " + textArea.getText()
                                , calenderDate.toString(), JOptionPane.PLAIN_MESSAGE);
                        // if illegal date was picked show the user a message
                    } catch (IllegalArgumentException illegalexc) {
                        JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
        JButton loadButton = new JButton("Load");
        panel.add(loadButton);
        // ActionListener when clicking on the load buttons
        loadButton.addActionListener(
                e -> {
                    try {
                        // create CalenderDate object with the selected date
                        CalenderDate calenderDate = new CalenderDate(Integer.parseInt(comboxBoxYear.getSelectedItem().toString()),
                                (comboxBoxMonth.getSelectedIndex() + 1),
                                Integer.parseInt(comboxBoxDay.getSelectedItem().toString()));
                        // show the comment that related to this CalenderDate
                        textArea.setText(calender.get(calenderDate));
                    } catch (NullPointerException ex) { // handle situation that the user load non .ser file
                        JOptionPane.showMessageDialog(null, "Can't load this comment.\n Please make sure you opened '.ser' file only",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
        return panel;
    }

    /**
     * Create date panel with day, month and year pickers
     *
     * @return JPanel
     */
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
        // the year picker is between 1970 - 2030
        for (int i = 0; i < year.length; i++)
            year[i] = i + 1970;
        comboxBoxYear = new JComboBox<>(year);

        panel.add(comboxBoxDay);
        panel.add(comboxBoxMonth);
        panel.add(comboxBoxYear);
        return panel;
    }

    /**
     * Create menu bar with file section that contain new, save, load and exit option
     *
     * @return JPanel
     */
    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //create file menu
        fileMenu.setMnemonic('F');

        JMenuItem newItem = new JMenuItem("New");
        newItem.setMnemonic('N');
        fileMenu.add(newItem); // add save to file menu
        newItem.addActionListener(
                e -> {
                    // show warning to the user when clicking on "New" option
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to save your current project first?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        saveToFile();
                    }
                    if (dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.YES_OPTION) {
                        textArea.setText("");
                        calender = new HashMap<>();
                    }
                });
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
                e -> openFile()
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

    /**
     * Open Serialized file and load it to a HashMap object
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser(); // open file chooser window with serialized files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized File", "ser");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION)
            return;
        File file = fileChooser.getSelectedFile();
        if (file != null && Files.exists(file.toPath())) {
            try {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
                calender = (HashMap<CalenderDate, String>) ois.readObject();
                textArea.setText("");
            } catch (IOException | ClassNotFoundException ioException) {
                JOptionPane.showMessageDialog(null, "Error opening file. Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Save the HashMap to a Serialized file
     */
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