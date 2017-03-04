import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressWarnings("ALL")
/**
 * Allows the user to add panels to an empty JFrame
 * The user can drag and drop each panel as well
 * as change the color of each panel by clicking on it
 *
 */
class DragDropGUI extends JFrame {

    private final List<JPanel> mypanels = new ArrayList<>();
    private JPanel panel;
    private JLabel panelNumber;
    private Random random;
    private Color randomColor;
    private JMenuItem addPanel;
    private JMenuItem deletePanels;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu panels;
    private JMenuItem mainMenu;
    private JMenuItem exitMenu;
    private JMenuItem deleteLastPanel;
    private JMenuItem addMultiPanels;
    private ColorChanger ml;
    private int x;
    private int y;
    private int i = 0;
    private int z = 0;
    private int panelCount = 1;


    public DragDropGUI(ActionListener e) {

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Menu");
        menu.setToolTipText("Main menu/Exit");
        menuBar.add(menu);

        panels = new JMenu("Panels");
        panels.setToolTipText("Add and delete panels");
        menuBar.add(panels);

        mainMenu = new JMenuItem("Main Menu");
        mainMenu.setToolTipText("Returns to the main menu");
        menu.add(mainMenu);
        mainMenu.addActionListener(e);

        exitMenu = new JMenuItem("Exit");
        exitMenu.setToolTipText("Exits the application");
        menu.add(exitMenu);
        exitMenu.addActionListener(e);

        addPanel = new JMenuItem("Add a Panel");
        addPanel.setToolTipText("Adds a new panel");
        panels.add(addPanel);

        addMultiPanels = new JMenuItem("Add 3 New Panels");
        addMultiPanels.setToolTipText("Adds 3 new panels");
        panels.add(addMultiPanels);

        deleteLastPanel = new JMenuItem("Delete Last Panel");
        deleteLastPanel.setToolTipText("Deletes the most recently added panel");
        panels.add(deleteLastPanel);

        deletePanels = new JMenuItem("Delete all Panels");
        deletePanels.setToolTipText("Deletes all panels currently showing");
        panels.add(deletePanels);

        ml = new ColorChanger();

        setSize(new Dimension(1000, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        initialize();
        addMouseListener(ml);

    }

    /**
     * Invoked when the user wants to add a panel
     */
    private void initialize() {

        addPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel();
                panelCount++;
            }
        });

        /**
         * Adds 3 panels when clicked.
         */
        addMultiPanels.addActionListener(new ActionListener() {
            /**
             * Invoked when "add three panels" button is clicked
             * Creats three panels and adds them to the frame
             * @param e
             */
            public void actionPerformed(ActionEvent e) {

                int loop = 0;
                while (loop < 3) {
                    addPanel();
                    loop++;
                    panelCount++;

                }
            }
        });

        /**
         * Deletes all panels by clearing the array
         * and repainting the frame
         */
        deletePanels.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!mypanels.isEmpty()) {

                    getContentPane().removeAll();
                    getContentPane().repaint();
                    panelCount = 1;
                }
            }

        });

        /**
         * Deletes last panel by taking the last panel
         * added and removing it from the array.
         * The frame is then repainted
         */
        deleteLastPanel.addActionListener(new ActionListener() {

            /**
             *
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                if (!mypanels.isEmpty()) {

                    final JPanel remove = mypanels.get(mypanels.size() - 1);
                    remove.setVisible(false);
                    mypanels.remove(mypanels.size() - 1);
                    getContentPane().repaint();
                    --panelCount;
                }
            }
        });
    }

    /**
     * Invoked when user hits one of the add panel buttons.
     * Creates a new panel everytime it is called.
     */
    public void addPanel() {

        random = new Random();
        i = random.nextInt(900);
        z = random.nextInt(450);
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        randomColor = new Color(r, g, b);

        ml = new ColorChanger();

        Font font = new Font("Courier", Font.BOLD, 50);

        panel = new JPanel(new GridLayout(1, 1));
        panelNumber = new JLabel(String.valueOf(panelCount));
        panelNumber.setVerticalAlignment(SwingConstants.CENTER);
        panelNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panelNumber.setFont(font);
        panel.setBounds(i, z, 100, 100);
        panel.setBackground(randomColor);
        panel.add(panelNumber);
        mypanels.add(panel);

        DragDropGUI.this.add(panel);
        DragDropGUI.this.repaint();
        DragDropGUI.this.handleDrag(panel);

        panel.setBorder(BorderFactory.createBevelBorder(ICONIFIED));
        panel.addMouseListener(ml);

    }

    /**
     * Invoked when the user drags a panel.  It takes the panels current
     * x, y coordinates then sets the panels location acording to the location
     * that it was dropped.
     *
     * @param panel
     */
    private void handleDrag(final JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                e.translatePoint(e.getComponent().getLocation().x - x, e.getComponent().getLocation().y - y);
                panel.setLocation(e.getX(), e.getY());
            }
        });
    }

    /**
     * Changes the color when the user clicks on a panel.
     * Uses a random number generator to creat a rgb code.
     */
    public class ColorChanger implements MouseMotionListener, MouseListener {

        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         * <p>
         * Makes the panel have a random color for a its background
         * everytime the user clicks on it
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            Random rand = new Random();
            Color randomColor;

            // Java 'Color' class takes 3 floats, from 0 to 1.
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            randomColor = new Color(r, g, b);
            ((Component) e.getSource()).setBackground(randomColor);

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        /**
         * Invoked when the mouse cursor has been moved onto a component
         * but no buttons have been pushed.
         *
         * @param e
         */
        @Override
        public void mouseMoved(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}