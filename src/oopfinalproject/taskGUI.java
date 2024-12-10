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
    private int progress;
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
		
		searchTextField = new JTextField();
		searchTextField.setBounds(18, 6, 168, 26);
		searchTextField.setForeground(SystemColor.inactiveCaptionText);
		searchTextField.setText("");
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(339, 12, 78, 20);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progress = selectedList.taskCompleteCounter() / selectedList.taskCounter();
		progressBar.setValue(progress);
		contentPane.add(progressBar);
		
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
		list.setBounds(28, 101, 150, 100);
		scrollPane1 = new JScrollPane(list);
		scrollPane1.setBounds(28, 101, 150, 100);
		contentPane.add(scrollPane1);
	
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
		contentPane.add(editBtn);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(e -> searchTasks(selectedList));
		searchBtn.setBounds(187, 6, 65, 29);
		contentPane.add(searchBtn);
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
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            tempList.remove(selectedIndex);
        }
        selectedList.tasks = tempList;
        todoLists.set(selectedListIndex, selectedList);
        FileManager.saveToDoLists(todoLists);
        updateTaskDisplay(selectedList);
	}
	
	private void addTask(list selectedList, List<Task> tempList, ArrayList<list> todoLists, int selectedListIndex) {
		Task temp1 = new Task(addTaskTextField.getText(), false);
		selectedList.tasks.add(temp1);
		todoLists.set(selectedListIndex, selectedList);
		FileManager.saveToDoLists(todoLists);
		String resultStr = temp1.getTaskName();
				if (temp1.isCompleted()) {
					resultStr+= " " + checkEmoji;
				}else {
					resultStr+= " " + xEmoji;
				}
				updateTaskDisplay(selectedList);
	}
	private void updateTaskDisplay(list selectedList) {
		listModel.clear();
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
	}
	

}