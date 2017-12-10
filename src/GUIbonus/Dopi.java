package GUIbonus;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import all_classes.Experr;
import all_classes.NewThread_one;
import inkass.AggregatesForLo;
import inkass.DataForInkass;
import inkass.DopiTerminals;
import services.HintTextFieldUI;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class Dopi extends JFrame{
	private static JTextField textField_NumbersTerminals;
	public void showDopi() {
		
		JFrame frame = new JFrame();     
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Experr.directory_res + "Ex5.png"));
		frame.setTitle("Добавление терминалов ДОПы");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(400, 400, 300, 250);
		frame.setBackground(new Color(102, 102, 255));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		frame.setVisible(true);
		panel.setVisible(true);
		
		textField_NumbersTerminals = new JTextField();
		textField_NumbersTerminals.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_NumbersTerminals.setBounds(10, 82, 272, 32);
		panel.add(textField_NumbersTerminals);
		textField_NumbersTerminals.setColumns(10);
		textField_NumbersTerminals.setUI(new HintTextFieldUI("Copy/Paste целиком таблички номера + примечания", false));
		
		JButton btnNewButton_Enter = new JButton("Применить");
		btnNewButton_Enter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_Enter.setForeground(new Color(204, 153, 204));
		btnNewButton_Enter.setBackground(new Color(51, 153, 204));
		btnNewButton_Enter.setBounds(83, 125, 119, 32);
		panel.add(btnNewButton_Enter);
		
		btnNewButton_Enter.addActionListener(new ActionListener() {
      	   public void actionPerformed(ActionEvent e) {
      		   
      		   new DopiTerminals().add();
      		 
      		   NewThread_one nth = new NewThread_one();
      		   nth.New_ThreadInkass();
      		   
//      		  DataForInkass dfi = new DataForInkass();
//		        	try {
//						dfi.addInputData();
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
		        	frame.setVisible(false);
      	   }
	        });
		
		JButton btnButton_parts = new JButton("Комплектуха");
		btnButton_parts.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnButton_parts.setForeground(new Color(204, 153, 204));
		btnButton_parts.setBackground(new Color(53, 53, 204));
		btnButton_parts.setBounds(70, 167, 145, 32);
		panel.add(btnButton_parts);
		btnButton_parts.addActionListener(new ActionListener() {
	      	   public void actionPerformed(ActionEvent e) {
	      		   new AggregatesForLo().data();	      		   
	      	   }
		        });
		
		
		JLabel lblNewLabel = new JLabel("Добавить  ДОПы");
		lblNewLabel.setForeground(new Color(153, 204, 204));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 28, 272, 25);
		panel.add(lblNewLabel);
	}
	public String  getNumbersTerminals() {
		return textField_NumbersTerminals.getText();
	}

	

}
