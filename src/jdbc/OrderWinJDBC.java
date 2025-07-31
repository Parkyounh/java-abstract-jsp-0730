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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

public class OrderWinJDBC extends JFrame {
	
	JLabel lb1 = new JLabel("주문 ID");
	JLabel lb2 = new JLabel("주문 상품");
	JLabel lb3 = new JLabel("주문 수량");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JButton btn1 = new JButton("저장");
	JButton btn2 = new JButton("불러오기");
	JButton btn3 = new JButton("수정");
	JButton btn4 = new JButton("삭제");
	
	JTextArea ta = new JTextArea();
	String id = "root";
	String pw = "1234";
	String url = "jdbc:mysql://localhost:3305/DoItSQL";
	Connection con1 = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "select * from order0729";
	String sql2 = "insert into order0729(order_id,product,quantity) values ";
	String sql3 = "update doit_dml set col_2 = '변경됨',col_1 =100 where col_1 =1";
	String sql4 ="delete from doit_dml where col_1 =1";
	

	
	public OrderWinJDBC() {
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
		con.add(btn4);
		btn4.setBounds(300, 100, 80, 40);
		
		ta.setEditable(false);
		
		JScrollPane sp = new JScrollPane(ta);
		
		sp.setBounds(30, 150, 250, 70);
		con.add(sp);
		
		this.setSize(500, 400);
		this.setLocation(800, 400);
		this.setTitle("주문 입력 시스템 v1.0");
		this.setVisible(true);
		load();
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String orderId = tf1.getText();
				String product = tf2.getText();
				int quantity = Integer.parseInt(tf3.getText());
				String str ="";
				str = String.format("insert into order0729(order_id,product,quantity) values ('%s','%s',%d)",orderId,product,quantity);

				System.out.println(str);
				try {
					stmt.execute(str);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				
			}
		});
	
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String orderId = tf1.getText();
				String product = tf2.getText();
				int quantity = Integer.parseInt(tf3.getText());
				String str ="";
				str = String.format("update order0729 set product = '%s' ,quantity = %d where order_id = '%s'" ,product,quantity,orderId);
				System.out.println(str);
				try {
					stmt.execute(str);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				load();
			}
		});
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String orderId = tf1.getText();
				String str ="";
				str = String.format("delete from order0729 where order_id ='%s'",orderId);
				System.out.println(str);
				try {
					stmt.execute(str);
				} catch (SQLException e1) {
				
				}
				
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				load();
			}
		});
		
	}
	public void load() {
		try {
			rs =stmt.executeQuery(sql);
			String str="";
			while(rs.next()) {
				
				String orderId= rs.getString("order_id");
				String product= rs.getString("product");
				int quantity= rs.getInt("quantity");
				
				str += String.format("%s\t%s\t%s\t\n",orderId,product,quantity);		
			}
			ta.setText(str);
		} catch (SQLException e1) {
			System.out.println("접속 오류");
		}		
	}

	public static void main(String[] args) {
		new OrderWinJDBC();

	}

}




