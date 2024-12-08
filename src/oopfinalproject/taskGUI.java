package oopfinalproject;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JCheckBox;

public class taskGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
    private final JList<String> list;  // Declare the list as final
    private final DefaultListModel<String> listModel; 
	private String checkEmoji = "✅";
	private String xEmoji = "❌";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taskGUI frame = new taskGUI(null, null, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public taskGUI(list selectedList, ArrayList<list> todoLists, int selectedListIndex) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 222, 224));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(18, 6, 168, 26);
		textField.setForeground(SystemColor.inactiveCaptionText);
		textField.setText("");
		contentPane.add(textField);
		textField.setColumns(10);
	
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(339, 12, 78, 20);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		int progress = selectedList.taskCompleteCounter() / selectedList.taskCounter();
		progressBar.setValue(progress);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel(selectedList.getTitle());
		lblNewLabel.setBounds(18, 44, 168, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(selectedList.getDescription());
		lblNewLabel_1.setBounds(18, 72, 226, 16);
		contentPane.add(lblNewLabel_1);
		
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		List<Task> tempList = selectedList.getTasks();
		for(Task t: tempList) {
			String temp = t.getTaskName();
			if (t.isCompleted()) {
				temp+= " " + checkEmoji;
			}else {
				temp+= " " + xEmoji;
			}
			listModel.addElement(temp);
		}
		list.setModel(listModel);
		list.setBackground(new Color(237, 238, 240));
		list.setBounds(28, 101, 150, 100);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setBounds(28, 101, 150, 100);
		contentPane.add(scrollPane1);
	
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(18, 214, 226, 26);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("Add Task");
		btnNewButton_1.setBounds(256, 214, 117, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task temp1 = new Task(textField_1.getText(), false);
				selectedList.tasks.add(temp1);
				todoLists.set(selectedListIndex, selectedList);
				main.saveDataToFile();
				String resultStr = temp1.getTaskName();
						if (temp1.isCompleted()) {
							resultStr+= " " + checkEmoji;
						}else {
							resultStr+= " " + xEmoji;
						}
				listModel.addElement(resultStr);
            }
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(349, 54, 94, 54);
		contentPane.add(btnNewButton_2);
	
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    tempList.remove(selectedIndex);
                }
                selectedList.tasks = tempList;
                todoLists.set(selectedListIndex, selectedList);
                main.saveDataToFile();
            }
        });

		JButton btnNewButton_2_1 = new JButton("Edit");
		btnNewButton_2_1.setBounds(349, 120, 94, 54);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> searchModel = new DefaultListModel<>();
		        for (Task t : selectedList.getTasks()) {
		            if (t.getTaskName().toLowerCase().contains(textField.getText())) {
		            	String temp = t.getTaskName();
		                if (t.isCompleted()) {
		                    temp += " " + checkEmoji;
		                } else {
		                    temp += " " + xEmoji;
		                }
		                searchModel.addElement(temp);
		            }
		        }
		        list.setModel(searchModel);
			}
		});
		btnNewButton.setBounds(187, 6, 65, 29);
		contentPane.add(btnNewButton);
	}


}