package oopfinalproject;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class editListGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField; 
    private JTextField textField_1;
    private JLabel lblNewLabel_2; 
    private JTextField editTaskField;
    private JButton saveBtn; 
    private JList<String> taskList;
    private DefaultListModel<String> listModel; 
    private JLabel lblNewLabel_3;
    
	private String checkEmoji = "✅";
	private String xEmoji = "❌";
	private JButton changeTaskBtn;
	private JScrollPane scrollPane_1;
	
	private JLabel lblNewLabel; 
	private JLabel lblNewLabel_1;
	private JToggleButton checkBtn;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editListGUI frame = new editListGUI(null, null, 0, null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public editListGUI(list selectedList, ArrayList<list> todoLists, int selectedListIndex, taskGUI frame) {
		System.out.println("CREATED GUI" + " " + selectedList.getTasksAsString());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ToDo List Title");
		lblNewLabel.setBounds(6, 6, 104, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField(selectedList.getTitle());
		textField.setBounds(125, 1, 241, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("ToDo List Description");
		lblNewLabel_1.setBounds(6, 39, 220, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField(selectedList.getDescription());
		textField_1.setColumns(10);
		textField_1.setBounds(16, 57, 241, 26);
		contentPane.add(textField_1);
		
		lblNewLabel_2 = new JLabel("ToDo List Tasks");
		lblNewLabel_2.setBounds(6, 95, 220, 16);
		contentPane.add(lblNewLabel_2);
		
		//enter editable list here
		listModel = new DefaultListModel<>();
		
		// add task data to list model
		for (Task t : selectedList.getTasks()) {
			System.out.println(t.getTaskName());
			String task = t.getTaskName();
			if (t.isCompleted()) {
				task += " " + checkEmoji;
			} else {
				task += " " + xEmoji;
			}
			listModel.addElement(task);
		}
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 123, 279, 66);
		contentPane.add(scrollPane_1);
		
	    taskList = new JList<>(listModel);
	    taskList.setForeground(new Color(0, 0, 0));
	    taskList.setBounds(16, 115, 241, 80);
	    scrollPane_1.setViewportView(taskList);
	    
	   	    
	    
	    // field to change task name
	    editTaskField = new JTextField("click on a task to edit");
	    editTaskField.setBounds(16, 205, 194, 26); 
	    
	    // toggle button to check or un-check a task
		checkBtn = new JToggleButton("-");
		
		// when pressing the button
		checkBtn.addActionListener(e -> {
			if (checkBtn.isSelected()) {
				checkBtn.setText(checkEmoji);
			} else {
				checkBtn.setText(xEmoji);
			}
		});
//			if (checkBtn.isSelected()) {
////				System.out.println("done");
//				checkBtn.setText(checkEmoji);
//				int index = taskList.getSelectedIndex();
//				if (index != -1) {
//					String tmp = editTaskField.getText();
//					tmp += " " + checkEmoji;
//					listModel.set(index, tmp);
//					checkBtn.setText(xEmoji);
//				}
//			} else {
////				System.out.println("not done");
//				checkBtn.setText(xEmoji);
//				int index = taskList.getSelectedIndex();
//				if (index != -1) {
//					String tmp = editTaskField.getText();
//					tmp += " " + xEmoji;
//					listModel.set(index, tmp);
//					checkBtn.setText(checkEmoji);
//				}
//			}
//			}
		
		checkBtn.setBounds(205, 205, 68, 29);
		contentPane.add(checkBtn);

	    
	    // each task has event listener
	    taskList.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
//	    		if (e.getClickCount() == 1) {
			    	int index = taskList.locationToIndex(e.getPoint());
			    	if (index != -1) {
			    		String tmp = listModel.get(index);	// get task that was clicked on
			    		String name = tmp.substring(0, tmp.length() - 2); // only show the name of the task
//			    		String tmp = selectedList.getTasks().get(index).getTaskName();
			    		editTaskField.setText(name);
			    		
			    		//boolean checked = listModel.get(index).isCompleted();
			    		// check if task is checked
			    		boolean checked = tmp.substring(tmp.length() - 1).equals(checkEmoji);
//			    		System.out.println(tmp.substring(tmp.length() - 1));
			    		if (checked) { 
			    			checkBtn.setText(checkEmoji);
			    			checkBtn.setSelected(true);
			    		} else {
			    			checkBtn.setText(xEmoji);
			    			checkBtn.setSelected(false);
			    		}
			    	}
	    		}
//	    	}
	    });
	    
	   
		    
	    
	    contentPane.add(editTaskField); 
	    
		// save updates to file
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(e -> {
			String title = textField.getText(); // save title
			String description = textField_1.getText();	// save description
			list l = new list(title, description);	// make new list object for tasks
			for (int i = 0; i < listModel.size(); i++) {
				String t = listModel.get(i);
				String name = t.substring(0, t.length() - 2);
				String completed = t.substring(t.length() - 1);
				boolean c = completed.equals(checkEmoji);
				//System.out.println(name + " " + completed + c);
				Task task = new Task(name, c);
				//System.out.println("hi " + task.isCompleted());
				l.addTask(task);
			}
			todoLists.set(selectedListIndex, l);	// update Array List of lists
			
//			for (int i = 0; i < todoLists.size(); i++) {
//				if (i == selectedListIndex) {
//					List<Task> temp = todoLists.get(i).getTasks();
//					for (int j = 0; j < temp.size(); j++) {
//						System.out.println("testing... " + temp.get(j).getTaskName() + " " + temp.get(j).isCompleted());
//					}
//				}
//			}

			// save everything to file
			FileManager.saveToDoLists(todoLists);
			ArrayList<list> lists = FileManager.loadToDoLists();
			frame.refreshData(selectedListIndex); // update the display of everything on TaskGUI
			this.dispose(); // close this frame
		});
		saveBtn.setBounds(6, 237, 117, 29);
		contentPane.add(saveBtn);
		
//		lblNewLabel_3 = new JLabel("Complete?");
//		lblNewLabel_3.setBounds(315, 95, 82, 16);
//		contentPane.add(lblNewLabel_3);
		
		changeTaskBtn = new JButton("Change Task");
		changeTaskBtn.setBounds(269, 205, 117, 29);
		
		// button to update one task
		changeTaskBtn.addActionListener(e -> {
			String updatedTask = editTaskField.getText();
			boolean checked = checkBtn.isSelected();
			if (checked) {
				updatedTask += " " + checkEmoji;
			} else {
				updatedTask += " " + xEmoji;
			}
			int index = taskList.getSelectedIndex();
			// update list model w/ new task name and whether it is checked or not
			if (index != -1) {
				listModel.set(index, updatedTask);
				editTaskField.setText(listModel.get(index).substring(0, listModel.get(index).length() - 2));
			}
			
		});
		contentPane.add(changeTaskBtn);
				
		
		
	}
}

