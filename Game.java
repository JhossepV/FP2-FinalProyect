import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    JFrame window;
    Container container;
    JButton fightButton;
    JPanel cardPanel;
    JPanel playerPanel;

    public Game() {

        // Configuración de la ventana de juego
        window = new JFrame();
        window.setTitle("Pokemon Battle");
        window.setSize(1280, 720);

        // Elementos visuales
        container = window.getContentPane();
        container.setLayout(null);

        // Estableciendo fondo
        BackgroudnPanel backgroundPanel = new BackgroudnPanel("FP2-FinalProyect/Images/Wallpaper/wallpaper1.jpg");
        container.add(backgroundPanel);
        backgroundPanel.setBounds(0, 0, 1280, 720);

        // Sección del botón "A Peleeear!"
        fightButton = new JButton("A Peleeear!");
        fightButton.setBounds(50, 300, 150, 50);
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "playerPanel");
            }
        });
        backgroundPanel.add(fightButton);

        // Segunda Sección
        cardPanel = new JPanel(new CardLayout());
        container.add(cardPanel);
        cardPanel.setBounds(0, 0, 1280, 720);
        cardPanel.add(backgroundPanel, "background");

        // Configuración elementos visuales en la sección "playerPanel"
        playerPanel = new JPanel(new BorderLayout());
        JButton player1Button = new JButton("Jugador 1");
        JButton player2Button = new JButton("Jugador 2");
        playerPanel.add(player1Button, BorderLayout.WEST);
        playerPanel.add(player2Button, BorderLayout.EAST);
        cardPanel.add(playerPanel, "playerPanel");

        // Crear instancia de Bulbasaur
        Bulbasaur bulbasaur = new Bulbasaur();

        // Configurar la imagen del jugador con la animación de Bulbasaur
        JLabel playerLabel = new JLabel();
        playerLabel.setIcon(new ImageIcon(bulbasaur.getCurrentAnimationFrame()));
        playerPanel.add(playerLabel, BorderLayout.CENTER);

        // Iniciar la animación de Bulbasaur
        bulbasaur.playAnimation();

        // Configurar el botón de combate en la sección "playerPanel"
        JButton backToFightButton = new JButton("Volver a Pelear");
        backToFightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "background");
            }
        });
        playerPanel.add(backToFightButton, BorderLayout.NORTH);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ImageIcon newIcon = new ImageIcon(bulbasaur.getCurrentAnimationFrame());
                playerLabel.setIcon(newIcon);
            }
        }, 0, 150);

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class BackgroudnPanel extends JPanel {
        private Image backgroundImage;

        public BackgroudnPanel(String w) {
            backgroundImage = new ImageIcon(w).getImage();
        }

        /*public Image getBackgroundImage() {
            return backgroundImage;
        }*/

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
