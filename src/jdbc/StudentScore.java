package jdbc;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentScore extends JFrame{
	JLabel lb1 = new JLabel("학번");
	JLabel lb2 = new JLabel("이름");
	JLabel lb3 = new JLabel("학점");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JButton btn1 = new JButton("저장");
	JButton btn2 = new JButton("수정");
	JButton btn3 = new JButton("삭제");
	JButton btn4 = new JButton("삭제");
	
	JTextArea ta = new JTextArea();
	String id = "root";
	String pw = "1234";
	String url = "jdbc:mysql://localhost:3305/DoItSQL";
	Connection con1 = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql1 = "select * from studentscore";
	String sql2 = "insert into studentscore(order_id,product,quantity) values ";
	String sql3 = "update studentscore set col_2 = '변경됨',col_1 =100 where col_1 =1";
	String sql4 ="delete from studentscore where col_1 =1";
	
    public StudentScore() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩");
			con1 = DriverManager.getConnection(url,id,pw);
			System.out.println("접속 성공");
			stmt = con1.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("접속 실패");
		}
		
		Container con = this.getContentPane();
		con.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		con.add(lb1);
		lb1.setBounds(30, 10, 80, 40);
		con.add(lb2);
		lb2.setBounds(120, 10, 80, 40);
		con.add(lb3);
		lb3.setBounds(210, 10, 80, 40);
		
		con.add(tf1);
		tf1.setBounds(30, 50, 80, 40);
		con.add(tf2);
		tf2.setBounds(120, 50, 80, 40);
		con.add(tf3);
		tf3.setBounds(210, 50, 80, 40);
		
		con.add(btn1);
		btn1.setBounds(30, 100, 80, 40);
		con.add(btn2);
		btn2.setBounds(120, 100, 80, 40);
		con.add(btn3);
		btn3.setBounds(210, 100, 80, 40);
//		con.add(btn4);
//		btn4.setBounds(300, 100, 80, 40);
		
		ta.setEditable(false);
		
		JScrollPane sp = new JScrollPane(ta);
		
		sp.setBounds(30, 150, 250, 70);
		con.add(sp);
		
		this.setSize(500, 400);
		this.setLocation(800, 400);
		this.setTitle("학생 정보 입력 프로그램");
		this.setVisible(true);
		load();
		
		
		
		btn1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int stdNum = Integer.parseInt(tf1.getText());
					String name= tf2.getText();
					double score= Double.parseDouble(tf3.getText());
					sql2 =String.format("insert into studentscore(stdnum,name,score) values (%d,'%s',%.2f)",stdNum,name,score);
					stmt.execute(sql2);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(con, "값을 입력해주세요");
				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				load();
			}
		});
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					int stdNum = Integer.parseInt(tf1.getText());
					String name= tf2.getText();
					double score= Double.parseDouble(tf3.getText());
					sql3 =String.format("update studentscore set name = '%s',score= %f where stdNum = %d",name,score,stdNum);
					stmt.execute(sql3);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(con, "값을 입력해주세요");

				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				load();
			}
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int stdNum = Integer.parseInt(tf1.getText());;
					sql4 =String.format("delete from studentscore where stdnum = %d",stdNum);
					stmt.execute(sql4);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(con, "학번을 입력해주세요");
				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				load();
			}
		});
		
		
		
    }
    public void load() {
    	sql1 = "select * from studentscore";
		try {
			rs =stmt.executeQuery(sql1);
			String str="";
			while(rs.next()) {
				
				int stdNum= rs.getInt("stdnum");
				String name= rs.getString("name");
				double score= rs.getDouble("score");
				
				str += String.format("%d\t%s\t%.2f\t\n",stdNum,name,score);		
			}
			ta.setText(str);
		} catch (SQLException e1) {
			System.out.println("접속 오류");
		}	
    }
    
    public static void main(String[] args) {
    	new StudentScore();
    }
    
}
