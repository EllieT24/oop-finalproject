package oopfinalproject;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class editListGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField; 
    private JTextField textField_1;
    private JLabel lblNewLabel_2; 
    private JTextField editTaskField;
    private JButton btnNewButton; 
    private JList<String> taskList;
    private DefaultListModel<String> listModel; 
    private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editListGUI frame = new editListGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public editListGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ToDo List Title");
		lblNewLabel.setBounds(6, 6, 104, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(125, 1, 241, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ToDo List Description");
		lblNewLabel_1.setBounds(6, 39, 220, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(16, 57, 241, 26);
		contentPane.add(textField_1);
		
		lblNewLabel_2 = new JLabel("ToDo List Tasks");
		lblNewLabel_2.setBounds(6, 95, 220, 16);
		contentPane.add(lblNewLabel_2);
		
		//enter editable list here
		 listModel = new DefaultListModel<>();
	        listModel.addElement("Task 1");
	        listModel.addElement("Task 2");
	        listModel.addElement("Task 3");

	        taskList = new JList<>(listModel);
	        taskList.setBounds(16, 115, 241, 80);
	        contentPane.add(taskList);

	        // Editable field for task updates
	        editTaskField = new JTextField();
	        editTaskField.setBounds(16, 205, 241, 26);
	        contentPane.add(editTaskField);

		
		btnNewButton = new JButton("Update");
		btnNewButton.setBounds(6, 237, 117, 29);
				
		contentPane.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("Complete?");
		lblNewLabel_3.setBounds(315, 95, 82, 16);
		contentPane.add(lblNewLabel_3);
		
	}
}