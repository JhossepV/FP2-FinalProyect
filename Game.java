import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Pokemon bulbasaur;
    private Pokemon pikachu;

    public Game() {
        // Configuración básica de la ventana
        setTitle("Juego por Turnos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Crear un CardLayout para gestionar los paneles
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear instancias de los Pokémon Bulbasaur y Pikachu
        bulbasaur = new Pokemon("Bulbasaur", 5, 100, "Images/Bulbasaur/bulbasaur.gif");
        pikachu = new Pokemon("Pikachu", 5, 100, "Images/Pikachu/pikachu.gif");

        // Crear el panel del menú con fondo
        JPanel menuPanel = createMenuPanel();
        cardPanel.add(menuPanel, "Menu");

        // Crear el panel del gameplay con fondo
        JPanel gameplayPanel = createGameplayPanel();
        cardPanel.add(gameplayPanel, "Gameplay");

        // Mostrar el panel del menú inicialmente
        cardLayout.show(cardPanel, "Menu");

        // Agregar el panel al contenedor de la ventana
        add(cardPanel);

        // Agregar ActionListener para manejar eventos de botones
        JButton btnNuevoJuego = findButton(menuPanel, "Nuevo Juego");
        btnNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar al panel de gameplay al presionar "Nuevo juego"
                cardLayout.show(cardPanel, "Gameplay");
            }
        });

        

        JButton btnSalir = findButton(menuPanel, "Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar lógica para salir del juego
                // En este caso, cerramos la aplicación
                System.exit(0);
            }
        });
    }

    private JButton findButton(Container container, String buttonText) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }
        return null;
    }

    private JPanel createMenuPanel() {
        // Crear un panel para el menú con un fondo
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo desde un archivo (ajusta la ruta según sea necesario)
                ImageIcon backgroundImage = new ImageIcon("Images/Wallpaper/wallpaper1.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        

        // Espacio horizontal al principio
        menuPanel.add(Box.createHorizontalStrut(20));

        // Espacio vertical arriba
        menuPanel.add(Box.createVerticalGlue());

        // Botones del menú
        JButton btnNuevoJuego = new JButton("Nuevo Juego");
        btnNuevoJuego.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño

        JButton btnCargarJuego = new JButton("Cargar Partida");
        btnCargarJuego.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño

        JButton btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño

        // Agregar botones al panel
        menuPanel.add(btnNuevoJuego);
        menuPanel.add(Box.createVerticalStrut(20)); // Espacio vertical
        menuPanel.add(btnCargarJuego);
        menuPanel.add(Box.createVerticalStrut(20)); // Espacio vertical
        menuPanel.add(btnSalir);

        // Espacio vertical abajo
        menuPanel.add(Box.createVerticalGlue());

        // Espacio horizontal al final
        menuPanel.add(Box.createHorizontalStrut(20));

        // Configurar alineación del panel al centro horizontal
        menuPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return menuPanel;
    }

    private JPanel createGameplayPanel() {
        // Crear un panel para el gameplay con un fondo
        JPanel gameplayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo desde un archivo (ajusta la ruta según sea necesario)
                ImageIcon backgroundImage = new ImageIcon("Images/Wallpaper/wallpaperBattle.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Usar un GridLayout para colocar las imágenes de los Pokémon uno al lado del otro
        GridLayout gridLayout = new GridLayout(2, 1);
        gameplayPanel.setLayout(gridLayout);

        // Mostrar imágenes de los Pokémon
        JLabel lblBulbasaur = new JLabel(bulbasaur.getSprite());
        JLabel lblPikachu = new JLabel(pikachu.getSprite());

        gameplayPanel.add(lblBulbasaur);
        gameplayPanel.add(lblPikachu);

        // Crear un panel para los botones de gameplay
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Botones del gameplay
        JButton btnAtaqueBasico = new JButton("Ataque Básico");
        btnAtaqueBasico.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño

        JButton btnHabilidadDefinitiva = new JButton("Habilidad Definitiva");
        btnHabilidadDefinitiva.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño

        // Botón para regresar al menú desde la sección de gameplay
        JButton btnVolverMenu = new JButton("Volver al Menú");
        btnVolverMenu.setPreferredSize(new Dimension(160, 60)); // Ajustar el tamaño
        btnVolverMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar al panel del menú al presionar "Volver al Menú"
                cardLayout.show(cardPanel, "Menu");
            }
        });

        // Agregar botones al panel
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnAtaqueBasico);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio horizontal
        buttonPanel.add(btnHabilidadDefinitiva);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio horizontal
        buttonPanel.add(btnVolverMenu);
        buttonPanel.add(Box.createHorizontalGlue());
        // Agregar el panel de botones al centro inferior
        gameplayPanel.add(buttonPanel, BorderLayout.SOUTH);

        return gameplayPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
}
