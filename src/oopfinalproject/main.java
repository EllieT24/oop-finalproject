package oopfinalproject;

<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.io.*;
=======
>>>>>>> 4c6a1a1bdaacdf4a49ab7fd36abb8c3230184cf7
import java.util.*;

public class main extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSearchForA;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private ArrayList<ToDoList> todoLists; // Store the list of ToDoList objects

<<<<<<< HEAD
    // Main method to run the application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main frame = new main();
                    frame.setVisible(true);  // Show the frame
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
=======
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchForA;
	static private Vector<List> list = new Vector<>();
>>>>>>> 4c6a1a1bdaacdf4a49ab7fd36abb8c3230184cf7

    // Constructor to create the frame and initialize components
    public main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(195, 194, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create the search bar and button
        JButton btnNewButton = new JButton("Search");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchList(txtSearchForA.getText());
            }
        });
        btnNewButton.setBounds(252, 6, 117, 29);
        contentPane.add(btnNewButton);

        txtSearchForA = new JTextField();
        txtSearchForA.setForeground(SystemColor.inactiveCaptionText);
        txtSearchForA.setText("Search for a list");
        txtSearchForA.setBounds(49, 6, 191, 26);
        contentPane.add(txtSearchForA);
        txtSearchForA.setColumns(10);

        // Create the panel to hold the list of to-do lists
        JPanel list_box = new JPanel();
        list_box.setBounds(50, 47, 267, 219);
        contentPane.add(list_box);

        // Set up the JList with DefaultListModel
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    showToDoListDetails(list.getSelectedIndex());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        list_box.setLayout(new BorderLayout());
        list_box.add(scrollPane, BorderLayout.CENTER);

        // Button to add a new to-do list
        JButton addListBtn = new JButton("Add New List");
        addListBtn.setBounds(327, 47, 117, 29);
        contentPane.add(addListBtn);

        addListBtn.addActionListener(e -> {
            // Open the list creation window
            ToDoListGUI listFrame = new ToDoListGUI(this);
            listFrame.setVisible(true);
        });

        // Button to delete the selected to-do list
        JButton deleteListBtn = new JButton("Delete");
        deleteListBtn.setBounds(327, 83, 117, 29);
        contentPane.add(deleteListBtn);
        deleteListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteList();
            }
        });

        // Load data from file when application starts
        loadDataFromFile();
    }

    // Method to load to-do lists from a file
    private void loadDataFromFile() {
        todoLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("todoList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.substring(1, line.length() - 1);  // Remove outer brackets
                    String[] parts = line.split(", ", 3);
                    String title = parts[0].trim();
                    String description = parts[1].trim();
                    String taskList = parts[2].trim();
                    ToDoList toDoList = new ToDoList(title, description);
                    toDoList.addTasks(Arrays.asList(taskList.split(", ")));
                    todoLists.add(toDoList);
                    listModel.addElement(toDoList.getTitle());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new to-do list and update the JList
    public void addToDoList(ToDoList newList) {
        todoLists.add(newList);  // Add the new list to the internal list
        listModel.addElement(newList.getTitle());  // Update the JList model
    }

    // Method to display the details of the selected to-do list
    private void showToDoListDetails(int selectedIndex) {
        if (selectedIndex != -1) {
            ToDoList selectedList = todoLists.get(selectedIndex);
            JOptionPane.showMessageDialog(this, "Description: " + selectedList.getDescription());
            // Additional functionality can be added to show tasks, mark them as complete, etc.
        }
    }

    // Method to delete the selected to-do list
    private void deleteList() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            todoLists.remove(selectedIndex);
            listModel.remove(selectedIndex);
            saveDataToFile();  // Save the updated list to the file
        }
    }

    // Method to search for a list based on the search query
    private void searchList(String query) {
        DefaultListModel<String> searchModel = new DefaultListModel<>();
        for (ToDoList toDoList : todoLists) {
            if (toDoList.getTitle().toLowerCase().contains(query.toLowerCase())) {
                searchModel.addElement(toDoList.getTitle());
            }
        }
        list.setModel(searchModel);  // Update the JList with search results
    }

    // Method to save the to-do lists to the file
    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("todoList.txt"))) {
            for (ToDoList toDoList : todoLists) {
                writer.write("[" + toDoList.getTitle() + ", " + toDoList.getDescription() + ", " + toDoList.getTasksAsString() + "]\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
