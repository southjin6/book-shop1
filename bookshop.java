package bookshop2bscs;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.Box;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class bookshop {
	
	
	Connection con;
	
	ResultSet rs;
	

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop2bscs","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
		
	
	private JFrame frmBookshop;
	private final JPanel panel = new JPanel();
	private JTextField txtbookname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtsearch;
	private JTable bookshoptable;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookshop window = new bookshop();
					window.frmBookshop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bookshop() {
		initialize();
		Connect();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookshop = new JFrame();
		frmBookshop.setForeground(Color.WHITE);
		frmBookshop.setFont(new Font("Arial Black", Font.BOLD, 16));
		frmBookshop.setTitle("Bookshop");
		frmBookshop.setBounds(100, 100, 641, 425);
		frmBookshop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookshop.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 625, 386);
		frmBookshop.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 22, 245, 225);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblbookname = new JLabel("Book Name:");
		lblbookname.setFont(new Font("Nirmala UI", Font.BOLD, 11));
		lblbookname.setBounds(10, 39, 70, 26);
		panel_1.add(lblbookname);
		
		txtbookname = new JTextField();
		txtbookname.setBounds(84, 43, 140, 20);
		panel_1.add(txtbookname);
		txtbookname.setColumns(10);
		
		JLabel lbledition = new JLabel("Edition:");
		lbledition.setFont(new Font("Nirmala UI", Font.BOLD, 11));
		lbledition.setBounds(10, 76, 70, 26);
		panel_1.add(lbledition);
		
		txtedition = new JTextField();
		txtedition.setBounds(84, 80, 140, 20);
		panel_1.add(txtedition);
		txtedition.setColumns(10);
		
		JLabel lblprice = new JLabel("Price:");
		lblprice.setFont(new Font("Nirmala UI", Font.BOLD, 11));
		lblprice.setBounds(10, 116, 46, 14);
		panel_1.add(lblprice);
		
		txtprice = new JTextField();
		txtprice.setBounds(84, 114, 140, 20);
		panel_1.add(txtprice);
		txtprice.setColumns(10);
		
		JButton btnsave = new JButton("SAVE");	
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = txtbookname.getText();
				String edition = txtedition.getText();
				String price = txtprice.getText();
				
				try {
					PreparedStatement pst = con.prepareStatement("INSERT INTO bookshoptable (id,name,edition,price)VALUES(?,?,?,?)");
					pst.setString(1,"0");
					pst.setString(2, name);
					pst.setString(3, edition);
					pst.setString(4, price);

					int k =pst.executeUpdate();
					System.out.println(k);
					if (k ==1) {
						JOptionPane.showMessageDialog(null,"Add Data Sucessfully !", "Bookshop", JOptionPane.INFORMATION_MESSAGE);
						table_load();
						txtbookname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbookname.requestFocus();
						
					}else {
						JOptionPane.showMessageDialog(null, "Record failed to save !","Bookshop", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
			});
		
			
		btnsave.setBounds(10, 161, 70, 23);
		panel_1.add(btnsave);
		
		JButton btnexit = new JButton("EXIT");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	}
		});
		btnexit.setBounds(84, 161, 70, 23);
		panel_1.add(btnexit);
		
		JButton btnclear = new JButton("CLEAR");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbookname.setText("");
				txtedition.setText("");
				txtprice.setText("");
			}
		});
		btnclear.setBounds(157, 161, 78, 23);
		panel_1.add(btnclear);
		
		JLabel lblsearch = new JLabel("Search Book:");
		lblsearch.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		lblsearch.setBounds(10, 270, 101, 14);
		panel.add(lblsearch);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel table = (DefaultTableModel) bookshoptable.getModel();
				String search = txtsearch.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				bookshoptable.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		txtsearch.setBounds(102, 269, 153, 20);
		panel.add(txtsearch);
		txtsearch.setColumns(10);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 DefaultTableModel table = (DefaultTableModel)bookshoptable.getModel();
		            try {
		            	   PreparedStatement pst;
		                   pst = con.prepareStatement("delete from bookshoptable where id =?");
		                   pst.setString(1, bid);
		                   
		                   int k = pst.executeUpdate();
		                   if (k==1) {
		                   JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		               
		                  
		                   idNumber.setText("");
		                   txtbookname.setText("");
		                   txtedition.setText("");
		                   txtprice.setText("");
		                   txtbookname.requestFocus();
		               }else {
		            	   JOptionPane.showMessageDialog(null, "Record Failed to Delete!!!!!");
		               			}
		                }
		               

		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }


			}
		});
		btndelete.setBounds(526, 277, 89, 23);
		panel.add(btndelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(278, 32, 337, 215);
		panel.add(scrollPane);
		
		bookshoptable = new JTable();
		scrollPane.setViewportView(bookshoptable);
		bookshoptable.setFillsViewportHeight(true);
		bookshoptable.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		bookshoptable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		bookshoptable.setCellSelectionEnabled(true);
		bookshoptable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		bookshoptable.setToolTipText("");
		bookshoptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop2bscs", "root","");
					Statement st = con.createStatement();
					String query="select * from bookshoptable";
					ResultSet rs =st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel)bookshoptable.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
					
					String id, name, edition ,price;
					while(rs.next()) {
						id = rs.getString(1);
						name = rs.getString(2);
						edition = rs.getString(3);
						price = rs.getString(4);
						String[] row = {id,name,edition,price};
						model.addRow(row);
					}
					st.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(405, 278, 89, 23);
		panel.add(btnUpdate);
		
		
	}
}
