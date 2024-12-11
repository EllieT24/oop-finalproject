package oopfinalproject;

import java.awt.EventQueue;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;

public class taskGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTextField addTaskTextField;
    private final JList<String> list;
    private final DefaultListModel<String> listModel; 
    private JProgressBar progressBar;
    private double progress;
    private JLabel titleLable;
    private JLabel descriptionLabel;
    private JScrollPane scrollPane1;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton editBtn;
    private JButton searchBtn;
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
		
				
		titleLable = new JLabel(selectedList.getTitle());
		titleLable.setBounds(18, 44, 168, 16);
		contentPane.add(titleLable);
		
		descriptionLabel = new JLabel(selectedList.getDescription());
		descriptionLabel.setBounds(18, 72, 226, 16);
		contentPane.add(descriptionLabel);
		
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
		list.setBounds(28, 101, 200, 100);
		scrollPane1 = new JScrollPane(list);
		scrollPane1.setBounds(28, 101, 200, 100);
		contentPane.add(scrollPane1);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(18, 6, 168, 26);
		searchTextField.setForeground(SystemColor.inactiveCaptionText);
		searchTextField.setText("");
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		
		progressBar = new JProgressBar(0, 100);
		progressBar.setBackground(new Color(245, 229, 248));
		progressBar.setForeground(new Color(249, 188, 253));
//		progressBar.setStringPainted(true);
		progressBar.setBounds(312, 6, 132, 20);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progress = 100 * (selectedList.taskCompleteCounter() / selectedList.taskCounter());
		System.out.println(selectedList.getTasksAsString());
		System.out.println(selectedList.taskCompleteCounter() + " / " + selectedList.taskCounter() + " = " + progress);
		progressBar.setValue((int) progress);
		contentPane.add(progressBar);

	
		addTaskTextField = new JTextField();
		addTaskTextField.setColumns(10);
		addTaskTextField.setBounds(18, 214, 226, 26);
		contentPane.add(addTaskTextField);
		
		addBtn = new JButton("Add Task");
		addBtn.setBounds(256, 214, 117, 29);
		addBtn.addActionListener(e -> addTask(selectedList, tempList, todoLists, selectedListIndex));
		
		contentPane.add(addBtn);
		
		deleteBtn = new JButton("Delete"); 
		deleteBtn.setBounds(349, 54, 94, 54);
		contentPane.add(deleteBtn);
		deleteBtn.addActionListener(e -> deleteTask(selectedList, tempList, todoLists, selectedListIndex));
		
		editBtn = new JButton("Edit");
		editBtn.setBounds(349, 120, 94, 54);
		editBtn.addActionListener(e -> {
			editListGUI editListFrame = new editListGUI(todoLists.get(selectedListIndex), todoLists, selectedListIndex, this);
			editListFrame.setVisible(true);
			updateTaskDisplay(selectedList);
		});
		contentPane.add(editBtn);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(e -> {
			searchTasks(todoLists.get(selectedListIndex));
		});
		searchBtn.setBounds(187, 6, 65, 29);
		contentPane.add(searchBtn);
		
		JButton clearSearchBtn = new JButton("Clear");
		clearSearchBtn.setBounds(246, 6, 65, 29);
		clearSearchBtn.addActionListener(e -> {
			searchTextField.setText("");
			list.setModel(listModel);
		});
		contentPane.add(clearSearchBtn);
	}
	
	private void searchTasks(list selectedList) {
		DefaultListModel<String> searchModel = new DefaultListModel<>();
        for (Task t : selectedList.getTasks()) {
            if (t.getTaskName().toLowerCase().contains(searchTextField.getText())) {
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
	
	private void deleteTask(list selectedList, List<Task> tempList, ArrayList<list> todoLists, int selectedListIndex) {
		int selectedIndex = list.getSelectedIndex();
		list l = todoLists.get(selectedListIndex);
		System.out.println(l.getTasksAsString());
		List<Task> temp = l.getTasks();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex); 
//            tempList.remove(selectedIndex); 
            temp.remove(selectedIndex);
            
        }
//        selectedList.tasks = tempList;	
        l.tasks = temp;
        todoLists.set(selectedListIndex, l); 
        FileManager.saveToDoLists(todoLists); 
        updateTaskDisplay(todoLists.get(selectedListIndex));	
	}
	
	private void addTask(list selectedList, List<Task> tempList, ArrayList<list> todoLists, int selectedListIndex) {
		Task temp1 = new Task(addTaskTextField.getText(), false);
		list l = todoLists.get(selectedListIndex);
		l.addTask(temp1);
		System.out.println(l.getTasksAsString());
		todoLists.set(selectedListIndex, l);
		FileManager.saveToDoLists(todoLists);
		String resultStr = temp1.getTaskName();
				if (temp1.isCompleted()) {
					resultStr+= " " + checkEmoji;
				}else {
					resultStr+= " " + xEmoji;
				}
				updateTaskDisplay(l);
		addTaskTextField.setText("");
	}
	
	private void updateTaskDisplay(list selectedList) {
		listModel.clear();
		List<Task> tempList = selectedList.getTasks();
		System.out.println("updating display....");
		for(Task t: tempList) {
			String temp = t.getTaskName();
			
			if (t.isCompleted()) {
				temp+= " " + checkEmoji;
			}else {
				temp+= " " + xEmoji;
			}
			listModel.addElement(temp);	
		}
	}
	
	// method to refresh list data (for editGUI)
	public void refreshData(int selectedListIndex) {
		ArrayList<list> todoLists = FileManager.loadToDoLists();
		titleLable.setText(todoLists.get(selectedListIndex).getTitle());
		descriptionLabel.setText(todoLists.get(selectedListIndex).getDescription());
		progress = 100 * (todoLists.get(selectedListIndex).taskCompleteCounter() / todoLists.get(selectedListIndex).taskCounter());
		progressBar.setValue((int) progress);
		updateTaskDisplay(todoLists.get(selectedListIndex));
	}
}