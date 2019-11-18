package ui;

import model.Item;
import model.RegularItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DisplayPanel extends JPanel implements ActionListener {

    ToDoList tryToDo;
    private JPanel panel;
    private JTextField nameField;
    private JLabel greetWithName = new JLabel("");
    private List<JCheckBox> checkBoxes = new ArrayList<>();

    public DisplayPanel() throws IOException, ParseException {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setBackground(Color.YELLOW);
        tryToDo = new ToDoList();
        initializeGreeting();
        initializeToDoList();
        add(panel);
        setVisible(true);
    }

    private void initializeGreeting() {
        JLabel title = new JLabel("Your To-Do List:");
        title.setFont(new Font("Serif", Font.BOLD, 25));

        JLabel greeting = new JLabel("Enter your name below:");
        nameField = new JTextField(15);
        JButton setNameButton = new JButton("Submit");
        setNameButton.setActionCommand("setName");
        setNameButton.addActionListener(this);

        panel.add(title);
        panel.add(greeting);
        panel.add(nameField);
        panel.add(setNameButton);
        panel.add(greetWithName);

    }

    private void initializeToDoList() throws IOException, ParseException {
        tryToDo.loadFile();
        tryToDo.toDoMap.forEach((k,i) -> checkBoxes.add(new JCheckBox(i.getContent(), false)));
        tryToDo.doneMap.forEach((k,i) -> checkBoxes.add(new JCheckBox(i.getContent(), true)));

        for (JCheckBox checkBox : checkBoxes) {
            panel.add(checkBox);
        }

        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);

        JButton addButton = new JButton("Add an item");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        panel.add(addButton);
        panel.add(saveButton);
        panel.add(quitButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("setName")) {
            JOptionPane.showMessageDialog(null, "Hello " + nameField.getText(), "", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("add")) {
            String addContent = JOptionPane.showInputDialog("Enter the content");
            try {
                addToToDo(addContent);
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                save();
            } catch (ParseException | FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void addToToDo(String inputContent) throws ParseException, IOException {
        Item item = new RegularItem();
        item.setThis(inputContent, new SimpleDateFormat(Item.FORMATTER_PATTERN).parse("11/12/2019"), false);
        saveAdded(inputContent, item);
        initializeToDoList();
    }

    private void saveAdded(String inputContent, Item inputItem) throws ParseException,
            FileNotFoundException, UnsupportedEncodingException {

        loadUpMaps();
        tryToDo.toDoMap.put(inputContent, inputItem);
        tryToDo.saveToFile();
    }

    private void save() throws ParseException, FileNotFoundException, UnsupportedEncodingException {
        loadUpMaps();
        tryToDo.saveToFile();

    }

    private void loadUpMaps() throws ParseException {
        tryToDo.toDoMap.clear();
        tryToDo.doneMap.clear();

        for (JCheckBox checkBox : checkBoxes) {
            Item item = new RegularItem();
            item.setThis(checkBox.getText(),
                    new SimpleDateFormat(Item.FORMATTER_PATTERN).parse("11/12/2019"),
                    checkBox.isSelected());

            if (checkBox.isSelected()) {
                tryToDo.doneMap.put(checkBox.getText(), item);
            } else {
                tryToDo.toDoMap.put(checkBox.getText(), item);
            }
        }
    }
}
