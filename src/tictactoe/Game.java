package tictactoe;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.Toolkit;

public class Game {

	private JFrame frmTicTacToe;
	private JTextField winnerfield;
	private boolean player1turn = true; //true - x false - o
	private char [] arr = {'1','2','3','4','5','6','7','8','9'};
	private JButton buttons[] = new JButton[9];
	private String winner = "";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Game window = new Game();
					window.frmTicTacToe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Game() {
		
		initialize();
	}
	
	
	//check for tie
	private void tieCheck() {
		boolean res=true;
		if(winner == "") {
			for(int i=0; i<9; i++) {
				if(arr[i] == (char)(49+i)) {
					res=false;
					break;
				}
			}
		}
		if(res) {
			JOptionPane.showMessageDialog(null,"There is a Tie. :-(\nClick reset button.");
		}
	}
	
	//check for winner
	private boolean winnerCheck() {
		boolean found=false;
		//horizontal check
		for(int i=0; i<=6; i=i+3) {
			if(arr[i] == arr[i+1] && arr[i+1]== arr[i+2]) {
				buttons[i].setBackground(new Color(255,255,153));
				buttons[i+1].setBackground(new Color(255,255,153));
				buttons[i+2].setBackground(new Color(255,255,153));
				if(player1turn) {
					winner="Player2";
				}else {
					winner="Player1";
				}
				found=true;
				break;
			}
		}
		//vertical check
		if(!found) {
			for(int i=0; i<3; i++) {
				if(arr[i] == arr[i+3] && arr[i]== arr[i+6]) {
					buttons[i].setBackground(new Color(255,255,153));
					buttons[i+3].setBackground(new Color(255,255,153));
					buttons[i+6].setBackground(new Color(255,255,153));
					if(player1turn) {
						winner="Player2";
					}else {
						winner="Player1";
					}
					found=true;
					break;
				}
			}
		}
		//1st diagonal
		if(!found) {
			if(arr[0]==arr[4] && arr[0]==arr[8]) {
				buttons[0].setBackground(new Color(255,255,153));
				buttons[4].setBackground(new Color(255,255,153));
				buttons[8].setBackground(new Color(255,255,153));
				if(player1turn) {
					winner="Player2";
				}else {
					winner="Player1";
				}
				found=true;
			}
		}
		//2nd diagonal
		if(!found) {
			if(arr[2]==arr[4] && arr[2]==arr[6]) {
				buttons[2].setBackground(new Color(255,255,153));
				buttons[4].setBackground(new Color(255,255,153));
				buttons[6].setBackground(new Color(255,255,153));
				if(player1turn) {
					winner="Player2";
				}else {
					winner="Player1";
				}
				found=true;
			}
		}
		if(found) {
			winnerfield.setText(winner);
			for(int i=0; i<9; i++) {
				buttons[i].setEnabled(false);
			}
			JOptionPane.showMessageDialog(null,"Winner is : "+winner+"\nClick reset button.");	
		}
		return found;
	}
	
	
	//change the player turn
	private void toggleturn() {
		if(player1turn) {
			player1turn = false;
		}else {
			player1turn = true;
		}
	}
	
	//reset the container 
	private void renew(String s) {
		for(int i=0; i<9; i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(new JButton().getBackground());
		}
		winnerfield.setText("");
		for(int i=0; i<9; i++) {
			arr[i]=(char) (49+i);
		}
		winner="";
		player1turn=true;
		JOptionPane.showMessageDialog(null,s);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTicTacToe = new JFrame();
		frmTicTacToe.setResizable(false);
		frmTicTacToe.setIconImage(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("/tictactoe/ticIcon.png")));
		frmTicTacToe.setForeground(UIManager.getColor("Button.foreground"));
		frmTicTacToe.setFont(new Font("Calibri", Font.PLAIN, 18));
		frmTicTacToe.setTitle("Tic Tac Toe");
		frmTicTacToe.getContentPane().setForeground(SystemColor.controlShadow);
		frmTicTacToe.setBackground(UIManager.getColor("Button.foreground"));
		frmTicTacToe.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("CheckBox.background"));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 204, 255));
		frmTicTacToe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		for(int i=0; i<9; i++) {
			buttons[i]=new JButton("");
		}
		
		
		
		buttons[0].setFont(new Font("Calibri", Font.PLAIN, 40));
		
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[0]=='1' && winner=="") {
					if(player1turn) {
						buttons[0].setText("X");
						buttons[0].setForeground(Color.RED);
						arr[0]='x';
				
					}else {
						buttons[0].setText("O");
						buttons[0].setForeground(Color.GREEN);
						arr[0]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
					
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[0].setBounds(33, 34, 102, 80);
		panel.add(buttons[0]);
		
		
		buttons[1].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[1]=='2' && winner=="") {
					if(player1turn) {
						buttons[1].setText("X");
						buttons[1].setForeground(Color.RED);
						arr[1]='x';
				
					}else {
						buttons[1].setText("O");
						buttons[1].setForeground(Color.GREEN);
						arr[1]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[1].setBounds(135, 34, 102, 80);
		panel.add(buttons[1]);
		
		
		buttons[2].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[2]=='3' && winner=="") {
					if(player1turn) {
						buttons[2].setText("X");
						buttons[2].setForeground(Color.RED);
						arr[2]='x';
				
					}else {
						buttons[2].setText("O");
						buttons[2].setForeground(Color.GREEN);
						arr[2]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[2].setBounds(237, 34, 102, 80);
		panel.add(buttons[2]);
		
		
		buttons[3].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[3]=='4' && winner=="") {
					if(player1turn) {
						buttons[3].setText("X");
						buttons[3].setForeground(Color.RED);
						arr[3]='x';
				
					}else {
						buttons[3].setText("O");
						buttons[3].setForeground(Color.GREEN);
						arr[3]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[3].setBounds(33, 114, 102, 80);
		panel.add(buttons[3]);
		
		buttons[4].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[4]=='5' && winner=="") {
					if(player1turn) {
						buttons[4].setText("X");
						buttons[4].setForeground(Color.RED);
						arr[4]='x';
				
					}else {
						buttons[4].setText("O");
						buttons[4].setForeground(Color.GREEN);
						arr[4]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[4].setBounds(135, 114, 102, 80);
		panel.add(buttons[4]);
		
		
		buttons[5].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[5]=='6' && winner=="") {
					if(player1turn) {
						buttons[5].setText("X");
						buttons[5].setForeground(Color.RED);
						arr[5]='x';
				
					}else {
						buttons[5].setText("O");
						buttons[5].setForeground(Color.GREEN);
						arr[5]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[5].setBounds(237, 114, 102, 80);
		panel.add(buttons[5]);
		
		
		buttons[6].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[6]=='7' && winner=="") {
					if(player1turn) {
						buttons[6].setText("X");
						buttons[6].setForeground(Color.RED);
						arr[6]='x';
				
					}else {
						buttons[6].setText("O");
						buttons[6].setForeground(Color.GREEN);
						arr[6]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[6].setBounds(33, 194, 102, 80);
		panel.add(buttons[6]);
		
		
		buttons[7].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[7]=='8' && winner=="") {
					if(player1turn) {
						buttons[7].setText("X");
						buttons[7].setForeground(Color.RED);
						arr[7]='x';
				
					}else {
						buttons[7].setText("O");
						buttons[7].setForeground(Color.GREEN);
						arr[7]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[7].setBounds(135, 194, 102, 80);
		panel.add(buttons[7]);
		
		
		buttons[8].setFont(new Font("Calibri", Font.PLAIN, 40));
		buttons[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr[8]=='9' && winner=="") {
					if(player1turn) {
						buttons[8].setText("X");
						buttons[8].setForeground(Color.RED);
						arr[8]='x';
				
					}else {
						buttons[8].setText("O");
						buttons[8].setForeground(Color.GREEN);
						arr[8]='o';
					}
					toggleturn();
					if(!winnerCheck()) {
						tieCheck();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Wrong action!\n(already clicked or someone has won.)");
				}
			}
		});
		buttons[8].setBounds(237, 194, 102, 80);
		panel.add(buttons[8]);
		
		JLabel label1 = new JLabel("X :  Player 1");
		label1.setFont(new Font("Calibri", Font.PLAIN, 18));
		label1.setForeground(new Color(204, 0, 0));
		label1.setBounds(381, 47, 95, 35);
		panel.add(label1);
		
		JLabel label2 = new JLabel("O :  Player 2");
		label2.setForeground(new Color(204, 0, 0));
		label2.setFont(new Font("Calibri", Font.PLAIN, 18));
		label2.setBounds(381, 103, 95, 35);
		panel.add(label2);
		
		JLabel wlabel = new JLabel("Winner");
		wlabel.setForeground(new Color(204, 0, 0));
		wlabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		wlabel.setBounds(389, 253, 64, 35);
		panel.add(wlabel);
		
		winnerfield = new JTextField();
		winnerfield.setBackground(new Color(255, 255, 51));
		winnerfield.setEditable(false);
		winnerfield.setBounds(366, 307, 110, 42);
		panel.add(winnerfield);
		winnerfield.setColumns(10);
		
		JButton resetbutton = new JButton("Reset");
		resetbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetbutton.setForeground(UIManager.getColor("Button.foreground"));
		resetbutton.setBackground(UIManager.getColor("Button.foreground"));
		
		resetbutton.setBounds(137, 307, 102, 42);
		panel.add(resetbutton);
		
		resetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renew("New Game Started \nWith Player1 as starter");
			}
		});
		frmTicTacToe.setBounds(200, 100, 530, 413);
		frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
