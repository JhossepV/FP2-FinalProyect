import javax.swing.*;
import java.awt.*;

public class Game {

    JFrame window;
    Container container;
    JLabel playerLabel;
    JLabel enemyLabel;
    JButton fightButton;
    
    public Game() {

        // Settings ventana de juego
        window = new JFrame();
        window.setTitle("Pokemon Battle");
        window.setSize(800, 600);
        
        // Elementos visuales
        container = window.getContentPane();
        container.setLayout(null);
        
        // Settings elementos visuales
        
        playerLabel = new JLabel();
        playerLabel.setBounds(100, 100, 200, 300);
        container.add(playerLabel);
        
        enemyLabel = new JLabel();
        enemyLabel.setBounds(500, 100, 200, 300);  
        container.add(enemyLabel);
        
        fightButton = new JButton("A Peleeear!");
        fightButton.setBounds(350, 400, 100, 50);
        fightButton.addActionListener(e -> {
            // Aca va a tener que ir la Lógica de combate

        });
        container.add(fightButton);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new Game();
    }
}