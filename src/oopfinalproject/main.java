package oopfinalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class main extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSearchForA;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private ArrayList<ToDoList> todoLists; // Store the list of ToDoList objects

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

    public main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(195, 194, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearch = new JLabel("Search");
        lblSearch.setBounds(49, 6, 60, 20);
        contentPane.add(lblSearch);

        txtSearchForA = new JTextField();
        txtSearchForA.setForeground(SystemColor.inactiveCaptionText);
        txtSearchForA.setText("");
        txtSearchForA.setBounds(115, 6, 191, 26);
        contentPane.add(txtSearchForA);
        txtSearchForA.setColumns(10);

        txtSearchForA.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchList(txtSearchForA.getText());
            }
        });

        JPanel list_box = new JPanel();
        list_box.setBounds(50, 47, 267, 219);
        contentPane.add(list_box);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        

        JScrollPane scrollPane = new JScrollPane(list);
        list_box.setLayout(new BorderLayout());
        list_box.add(scrollPane, BorderLayout.CENTER);

        JButton addListBtn = new JButton("Add New List");
        addListBtn.setBounds(327, 47, 117, 29);
        contentPane.add(addListBtn);

        addListBtn.addActionListener(e -> {
            ToDoListGUI listFrame = new ToDoListGUI(this);
            listFrame.setVisible(true);
        });

        JButton deleteListBtn = new JButton("Delete");
        deleteListBtn.setBounds(327, 83, 117, 29);
        contentPane.add(deleteListBtn);
        deleteListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteList();
            }
        });

        loadDataFromFile();
    }

    private void loadDataFromFile() {
        todoLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("todoList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.substring(1, line.length() - 1);
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

    public void addToDoList(ToDoList newList) {
        todoLists.add(newList);
        listModel.addElement(newList.getTitle());
        updateListDisplay(); // Refresh the JList after adding
    }

    private void updateListDisplay() {
        DefaultListModel<String> newListModel = new DefaultListModel<>();
        for (ToDoList list : todoLists) {
            newListModel.addElement(list.getTitle());
        }
        list.setModel(newListModel); // Update the JList model
    }

    private void deleteList() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            todoLists.remove(selectedIndex);
            listModel.remove(selectedIndex);
            saveDataToFile();
        }
    }

    private void searchList(String query) {
        DefaultListModel<String> searchModel = new DefaultListModel<>();
        for (ToDoList toDoList : todoLists) {
            if (toDoList.getTitle().toLowerCase().contains(query.toLowerCase())) {
                searchModel.addElement(toDoList.getTitle());
            }
        }
        list.setModel(searchModel);
    }

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

