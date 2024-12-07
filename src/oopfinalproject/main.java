package oopfinalproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchForA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(195, 194, 228));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JPanel list_box = new JPanel();
		list_box.setBounds(50, 47, 267, 219);
		contentPane.add(list_box);
		
		JList list = new JList();
		list_box.add(list);
		
		JButton addListBtn = new JButton("Add New List");
		addListBtn.setBounds(327, 47, 117, 29);
		contentPane.add(addListBtn);
		
		JButton deleteListBtn = new JButton("Delete");
		deleteListBtn.setBounds(327, 83, 117, 29);
		contentPane.add(deleteListBtn);
	}
}
