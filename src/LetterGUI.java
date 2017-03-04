import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("ALL")
class LetterGUI extends JFrame implements KeyListener{

	private final JLabel label;

	public LetterGUI(ActionListener e){

		int height = 300;
		int width = 300;
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		label = new JLabel("A");
		label.setFont(new Font("Georgia", Font.PLAIN, 200));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 300, 250);
		label.setForeground(Color.RED);
		panel.setBackground(Color.CYAN);
		this.addKeyListener(this);

		panel.add(label);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mainMenu = new JMenuItem("Main Menu");
		mnMenu.add(mainMenu);
		mainMenu.addActionListener(e);

		JMenuItem exitMenu = new JMenuItem("Exit");
		mnMenu.add(exitMenu);
		exitMenu.addActionListener(e);

	}

	/**
	 * Invoked when the space key is pressed
	 * Changes the color to the three specified
	 * colors
	 * @param e
	 */
	public void keyPressed(KeyEvent e){
		
		char text = e.getKeyChar();

		if(text <= 122 && text >= 97){
			label.setText("" + text);
			
		}else if(text == 32){
			
			if(label.getForeground().equals(Color.RED)){
				label.setForeground(Color.BLUE);
			}else if(label.getForeground().equals(Color.BLUE)){
				label.setForeground(Color.YELLOW);
			}else if(label.getForeground().equals(Color.YELLOW)){
				label.setForeground(Color.RED);
			}else if(label.getForeground().equals(Color.BLACK)){
				label.setForeground(Color.RED);
			}
			//Error message
		}else {
			
			JOptionPane.showMessageDialog(label, "Use A-Z or Space only!", "Error", JOptionPane.ERROR_MESSAGE);
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	

	
	



