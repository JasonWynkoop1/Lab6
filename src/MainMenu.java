import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainMenu holds the two options for
 * letterGUI and DragDropGUI.
 */
class MainMenu extends JFrame{

    private final JButton panelMover;
    private final JButton letterChanger;
    private final DragDropGUI ddg;
    private final LetterGUI lg;

    public MainMenu(){

        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font font = new Font("Courier", Font.BOLD,12);
        MenuListener ml = new MenuListener();

        JPanel header = new JPanel();
        add(header, BorderLayout.NORTH);
        JLabel title = new JLabel("Please Choose an Option Below");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(font);
        header.add(title);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setBackground(Color.GRAY);
        add(panel, BorderLayout.CENTER);

        panelMover = new JButton("Panel Mover");
        panel.add(panelMover);
        panelMover.addActionListener(ml);

        letterChanger = new JButton("Letter Changer");
        panel.add(letterChanger);
        letterChanger.addActionListener(ml);

        ddg = new DragDropGUI(new MenuListener());
        lg = new LetterGUI(new MenuListener());

        setVisible(true);

    }

    /**
     * Invoked when a button is clicked
     * this is used in every class
     */
    private class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equals("Exit")){
                System.exit(0);
            }else if(e.getSource() == panelMover){
                ddg.repaint();
                ddg.setVisible(true);
                setVisible(false);
                setVisible(false);
            }else if(e.getSource() == letterChanger){
                lg.setVisible(true);
                lg.repaint();

                ddg.setVisible(false);
                setVisible(false);

            }else if(e.getActionCommand().equals("Main Menu")){
                lg.setVisible(false);
                ddg.setVisible(false);
                setVisible(true);
            }

        }

    }

}