package oopfinalproject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Arrays;

public class listGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEnterTitleHere;
    private JTextField txtEnterDescriptions;
    private JTextField txtSeperateTasksBy;
    private main mainFrame;
    private JLabel lblNewLabel; 
    private JLabel lblNewLabel_1; 
    private JLabel lblNewLabel_2;
    private JButton btnNewButton;

    public listGUI(main mainFrame) {
        this.mainFrame = mainFrame; 	

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        lblNewLabel = new JLabel("ToDo List Title");
        lblNewLabel.setBounds(6, 0, 98, 16);
        getContentPane().add(lblNewLabel);

        txtEnterTitleHere = new JTextField();
        txtEnterTitleHere.setText("Enter Title here");
        txtEnterTitleHere.setBounds(6, 16, 130, 26);
        getContentPane().add(txtEnterTitleHere);
        txtEnterTitleHere.setColumns(10);

        lblNewLabel_1 = new JLabel("ToDo List Description");
        lblNewLabel_1.setBounds(6, 54, 144, 16);
        getContentPane().add(lblNewLabel_1);

        txtEnterDescriptions = new JTextField();
        txtEnterDescriptions.setText("Enter Description here");
        txtEnterDescriptions.setBounds(6, 72, 259, 26);
        getContentPane().add(txtEnterDescriptions);
        txtEnterDescriptions.setColumns(10);

        lblNewLabel_2 = new JLabel("ToDo List Tasks");
        lblNewLabel_2.setBounds(6, 110, 107, 16);
        getContentPane().add(lblNewLabel_2);

        txtSeperateTasksBy = new JTextField();
        txtSeperateTasksBy.setText("Separate tasks by commas");
        txtSeperateTasksBy.setBounds(6, 131, 259, 26);
        getContentPane().add(txtSeperateTasksBy);
        txtSeperateTasksBy.setColumns(10); 

        btnNewButton = new JButton("Save");  
        btnNewButton.setBounds(327, 237, 117, 29);
        getContentPane().add(btnNewButton);

       
        btnNewButton.addActionListener(e -> {
            String title = txtEnterTitleHere.getText();
            String description = txtEnterDescriptions.getText();
            String tasks = txtSeperateTasksBy.getText();
            list newList = new list(title, description);
            newList.addTasks(Arrays.asList(tasks.split(", ")));
            
            FileManager.addNewList(newList);
            mainFrame.refreshList();

            dispose();
        });
    }
}