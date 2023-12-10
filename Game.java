import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    JFrame window;
    Container container;
    JLabel playerLabel;
    JLabel enemyLabel;
    JButton fightButton;
    JPanel cardPanel;
    
    public Game() {

        // Settings ventana de juego
        window = new JFrame();
        window.setTitle("Pokemon Battle");
        window.setSize(1280, 720);

        // Elementos visuales
        container = window.getContentPane();
        container.setLayout(null);
        
        // Estableciendo fondo
        BackgroudPanel backgroudPanel = new BackgroudPanel();
        container.add(backgroudPanel);
        backgroudPanel.setBounds(0, 0, 1280, 720);

        // Segunda Seccion
        cardPanel = new JPanel(new CardLayout());
        container.add(cardPanel);
        cardPanel.setBounds(0, 0, 1280, 720);

        cardPanel.add(backgroudPanel, "background");

        // Settings elementos visuales
        
        JPanel playerPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroudPanel.getBackgroundImage(), 0, 0, this);
            }
        };
        JButton player1Button = new JButton("Jugador 1");
        JButton player2Button = new JButton("Jugador 2");
        playerPanel.add(player1Button);
        playerPanel.add(player2Button);
        cardPanel.add(playerPanel, "playerPanel");

        playerLabel = new JLabel();
        playerLabel.setBounds(100, 100, 200, 300);
        container.add(playerLabel);
        
        enemyLabel = new JLabel();
        enemyLabel.setBounds(500, 100, 200, 300);  
        container.add(enemyLabel);
        
        fightButton = new JButton("A Peleeear!");
        fightButton.setBounds(590, 520, 100, 50);
        fightButton.addActionListener(new ActionListener() {
            // Aca va a tener que ir la Lógica de combate
            @Override
            public void actionPerformed(ActionEvent e){
                CardLayout cardLayoud = (CardLayout) cardPanel.getLayout();
                cardLayoud.show(cardPanel, "playerPanel");
            }
        }); 
        
        backgroudPanel.add(fightButton);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    private class BackgroudPanel extends JPanel{
        private Image backgroundImage;

        public BackgroudPanel(){
            backgroundImage = new ImageIcon("FP2-FinalProyect/Images/wallpaper1.jpg").getImage();
        }
        public Image getBackgroundImage(){
            return backgroundImage;
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
    
    
    public static void main(String[] args) {
        new Game();
    }
}