import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Para una mejor experiencia, maximizar la ventana del juego

public class Game extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Pokemon player1Pokemon;
    private Pokemon player2Pokemon;

    private boolean isPlayer1Turn;

    private JButton btnAttack1Player1;
    private JButton btnDefendPlayer1;
    private JButton btnUltimatePlayer1;
    private JButton btnHealPlayer1;

    private JButton btnAttack1Player2;
    private JButton btnDefendPlayer2;
    private JButton btnUltimatePlayer2;
    private JButton btnHealPlayer2;

    private int defenseCooldownPlayer1;
    private int defenseCooldownPlayer2;

    private JTextArea messageTextArea;

    public Game() {
        setTitle("PVP POKEMON");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainMenuPanel = createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "MainMenu");

        ArrayList<Pokemon> allPokemon = new ArrayList<>();
        allPokemon.add(new Bulbasaur());
        allPokemon.add(new Pikachu());
        allPokemon.add(new Charmander());
        allPokemon.add(new Squirtle());

        JPanel selectionPanel = createSelectionPanel(allPokemon);
        cardPanel.add(selectionPanel, "Selection");

        JPanel finalPanel = createFinalPanel();
        cardPanel.add(finalPanel, "Final");

        cardLayout.show(cardPanel, "MainMenu");

        add(cardPanel);

        JButton btnNewGame = findButton(mainMenuPanel, "Nuevo Juego");
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Selection");
            }
        });

        Font buttonFont = new Font("Monospaced", Font.BOLD, 20);

        btnAttack1Player1 = new JButton("Ataque Basico");
        btnAttack1Player1.setFont(buttonFont);
        btnDefendPlayer1 = new JButton("Defender");
        btnDefendPlayer1.setFont(buttonFont);
        btnUltimatePlayer1 = new JButton("Habilidad Definitiva");
        btnUltimatePlayer1.setFont(buttonFont);
        btnHealPlayer1 = new JButton("Curar");
        btnHealPlayer1.setFont(buttonFont);

        btnAttack1Player2 = new JButton("Ataque Basico");
        btnAttack1Player2.setFont(buttonFont);
        btnDefendPlayer2 = new JButton("Defender");
        btnDefendPlayer2.setFont(buttonFont);
        btnUltimatePlayer2 = new JButton("Habilidad Definitiva");
        btnUltimatePlayer2.setFont(buttonFont);
        btnHealPlayer2 = new JButton("Curar");
        btnHealPlayer2.setFont(buttonFont);
    }

    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("menu.jpeg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        JLabel lblTitle = new JLabel("PVP POKEMON");
        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 100));
        lblTitle.setForeground(Color.WHITE);
        mainMenuPanel.add(lblTitle, gbc);

        Font buttonFont = new Font("Monospaced", Font.BOLD, 24);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        JButton btnNewGame = new JButton("Nuevo Juego");
        btnNewGame.setFont(buttonFont);
        btnNewGame.setForeground(Color.BLACK);
        btnNewGame.setPreferredSize(new Dimension(300, 60));
        mainMenuPanel.add(btnNewGame, gbc);

        gbc.gridy = 2;
        JButton btnLoadGame = new JButton("Cargar Partida");
        btnLoadGame.setFont(buttonFont);
        btnLoadGame.setForeground(Color.BLACK);
        btnLoadGame.setPreferredSize(new Dimension(300, 60));
        mainMenuPanel.add(btnLoadGame, gbc);

        gbc.gridy = 3;
        JButton btnExit = new JButton("Salir");
        btnExit.setFont(buttonFont);
        btnExit.setForeground(Color.BLACK);
        btnExit.setPreferredSize(new Dimension(300, 60));
        mainMenuPanel.add(btnExit, gbc);

        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Selection");
            }
        });

        btnLoadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGameState();
                cardLayout.show(cardPanel, "Gameplay");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return mainMenuPanel;
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

    private void saveGameState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gamestate.ser"))) {
            GameState gameState = new GameState(player1Pokemon, player2Pokemon, isPlayer1Turn,
                    defenseCooldownPlayer1, defenseCooldownPlayer2);
            oos.writeObject(gameState);
            System.out.println("Partida guardada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamestate.ser"))) {
            GameState gameState = (GameState) ois.readObject();
            player1Pokemon = gameState.getPlayer1Pokemon();
            player2Pokemon = gameState.getPlayer2Pokemon();
            isPlayer1Turn = gameState.isPlayer1Turn();
            defenseCooldownPlayer1 = gameState.getDefenseCooldownPlayer1();
            defenseCooldownPlayer2 = gameState.getDefenseCooldownPlayer2();

            System.out.println("Partida cargada exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JPanel createSelectionPanel(ArrayList<Pokemon> allPokemon) {
        JPanel selectionPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("selection2.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        selectionPanel.setLayout(new BorderLayout());

        Font btnTitleFont = new Font("Monospaced", Font.BOLD, 60);
        JLabel lblTitle = new JLabel("SELECCION DE POKEMON");
        lblTitle.setFont(btnTitleFont);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        selectionPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel innerPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        innerPanel.setOpaque(false);

        for (Pokemon pokemon : allPokemon) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());
            pokemonPanel.setOpaque(false);

            JLabel lblPokemon = new JLabel(pokemon.getSprite());
            pokemonPanel.add(lblPokemon, BorderLayout.CENTER);

            Font buttonFont = new Font("Monospaced", Font.BOLD, 28);
            JButton btnSelectPokemon = new JButton("Seleccionar " + pokemon.getName());
            btnSelectPokemon.setPreferredSize(new Dimension(200, 60));
            btnSelectPokemon.setFont(buttonFont);
            // btnSelectPokemon.setForeground(Color.WHITE);

            btnSelectPokemon.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player1Pokemon == null) {
                        player1Pokemon = pokemon;
                        JOptionPane.showMessageDialog(null, "Jugador 1 seleccionó a " + pokemon.getName());
                    } else if (player2Pokemon == null) {
                        player2Pokemon = pokemon;
                        JOptionPane.showMessageDialog(null, "Jugador 2 seleccionó a " + pokemon.getName());
                        isPlayer1Turn = true;
                        startGame();
                    }
                }
            });

            pokemonPanel.add(btnSelectPokemon, BorderLayout.SOUTH);
            innerPanel.add(pokemonPanel);
        }

        selectionPanel.add(innerPanel, BorderLayout.CENTER);

        return selectionPanel;
    }

    private JPanel createGameContainerPanel(JPanel gameplayPanel) {
        JPanel gameContainerPanel = new JPanel(new BorderLayout());
        gameContainerPanel.add(gameplayPanel, BorderLayout.CENTER);
        cardPanel.add(gameContainerPanel, "Gameplay");
        return gameContainerPanel;
    }

    private JPanel createGameplayPanel() {
        JPanel gameplayPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("wallpaperBattle.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        gameplayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        if (player1Pokemon != null && player2Pokemon != null) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 40, 0);
            JLabel lblPlayer1 = new JLabel(player1Pokemon.getSprite());
            gameplayPanel.add(lblPlayer1, gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 2;
            JLabel lblPlayer2 = new JLabel(player2Pokemon.getSprite());
            gameplayPanel.add(lblPlayer2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            gbc.weighty = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            gameplayPanel.add(btnAttack1Player1, gbc);

            gbc.gridx = 1;
            gameplayPanel.add(btnDefendPlayer1, gbc);

            gbc.gridx = 2;
            gameplayPanel.add(btnAttack1Player2, gbc);

            gbc.gridx = 3;
            gameplayPanel.add(btnDefendPlayer2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gameplayPanel.add(btnUltimatePlayer1, gbc);

            gbc.gridx = 1;
            gameplayPanel.add(btnHealPlayer1, gbc);

            gbc.gridx = 2;
            gameplayPanel.add(btnUltimatePlayer2, gbc);

            gbc.gridx = 3;
            gameplayPanel.add(btnHealPlayer2, gbc);

            Font playerNameFont = new Font("Monospaced", Font.BOLD, 40);
            JProgressBar healthBarPlayer1 = new JProgressBar(0, 100);
            healthBarPlayer1.setStringPainted(true);
            healthBarPlayer1.setValue(100);
            JLabel lblPlayer1Name = new JLabel(player1Pokemon.getName());
            lblPlayer1Name.setFont(playerNameFont);
            lblPlayer1Name.setForeground(Color.WHITE);

            JProgressBar healthBarPlayer2 = new JProgressBar(0, 100);
            healthBarPlayer2.setStringPainted(true);
            healthBarPlayer2.setValue(100);
            JLabel lblPlayer2Name = new JLabel(player2Pokemon.getName());
            lblPlayer2Name.setFont(playerNameFont);
            lblPlayer2Name.setForeground(Color.WHITE);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gameplayPanel.add(healthBarPlayer1, gbc);

            gbc.gridx = 3;
            gbc.gridwidth = 2;
            gameplayPanel.add(healthBarPlayer2, gbc);

            // gbc.gridheight = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gameplayPanel.add(lblPlayer1Name, gbc);

            gbc.weighty = 1;
            gbc.gridx = 3;
            gbc.gridwidth = 1;
            gameplayPanel.add(lblPlayer2Name, gbc);

            Timer timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkGameOver();
                    player1Pokemon.updateHealthBar(healthBarPlayer1);
                    player2Pokemon.updateHealthBar(healthBarPlayer2);
                }
            });
            timer.start();

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.gridwidth = 4;
            Font btnMenu = new Font("Monospaced", Font.BOLD, 20);
            JButton btnBackToMenu = new JButton("Volver al Menú");
            btnBackToMenu.setFont(btnMenu);
            btnBackToMenu.setPreferredSize(new Dimension(230, 60));
            gbc.insets = new Insets(70, 0, 0, 0);
            gameplayPanel.add(btnBackToMenu, gbc);

            messageTextArea = new JTextArea();
            messageTextArea.setEditable(false);
            messageTextArea.setLineWrap(true);
            messageTextArea.setWrapStyleWord(true);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 4;
            gbc.insets = new Insets(20, 0, 0, 0);
            JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
            messageScrollPane.setPreferredSize(new Dimension(400, 100));
            gameplayPanel.add(messageScrollPane, gbc);

            JButton btnSaveGame = new JButton("Guardar");
            btnSaveGame.setFont(btnMenu);
            btnSaveGame.setPreferredSize(new Dimension(200, 60));
            gbc.gridx = 3;
            gbc.gridy = 9;
            gbc.gridwidth = 2;
            gameplayPanel.add(btnSaveGame, gbc);

            btnSaveGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveGameState();
                }
            });

            btnHealPlayer1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isPlayer1Turn) {
                        player1Pokemon.heal();
                        switchTurns();
                    }
                }
            });

            btnDefendPlayer2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!isPlayer1Turn && defenseCooldownPlayer1 == 0) {
                        player1Pokemon.defend();
                        switchTurns();
                        defenseCooldownPlayer1 = 2;
                    }
                }
            });

            btnDefendPlayer1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isPlayer1Turn && defenseCooldownPlayer2 == 0) {
                        player2Pokemon.defend();
                        switchTurns();
                        defenseCooldownPlayer2 = 2;
                    }
                }
            });

            btnHealPlayer2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!isPlayer1Turn) {
                        player2Pokemon.heal();
                        switchTurns();
                    }
                }
            });

            btnAttack1Player2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!isPlayer1Turn) {
                        player2Pokemon.attack(player1Pokemon, player2Pokemon.getAttacks().get(0));
                        switchTurns();
                    }
                }
            });

            btnAttack1Player1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isPlayer1Turn) {
                        player1Pokemon.attack(player2Pokemon, player1Pokemon.getAttacks().get(0));
                        switchTurns();
                    }
                }
            });
            btnUltimatePlayer1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (isPlayer1Turn) {
                        player1Pokemon.attack(player2Pokemon, player1Pokemon.getAttacks().get(0));
                        switchTurns();
                    }
                }
            });

            btnUltimatePlayer2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!isPlayer1Turn) {
                        player2Pokemon.attack(player1Pokemon, player2Pokemon.getAttacks().get(0));
                        switchTurns();
                    }
                }
            });

            btnBackToMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetGame();
                    cardLayout.show(cardPanel, "MainMenu");
                }
            });
        }

        updateButtonVisibility();

        return gameplayPanel;
    }

    private void checkGameOver() {
        if (player1Pokemon.getHp() <= 0) {
            showFinalScreen("Gana Jugador 2");
        } else if (player2Pokemon.getHp() <= 0) {
            showFinalScreen("Gana Jugador 1");
        }
    }

    private void showFinalScreen(String winnerText) {
        JLabel lblWinner = new JLabel(winnerText);
        lblWinner.setFont(new Font("Monospaced", Font.BOLD, 90));  
        lblWinner.setForeground(new Color(255, 215, 0));
        lblWinner.setBorder(new LineBorder(Color.WHITE, 4));
        lblWinner.setVerticalAlignment(JLabel.CENTER);
        lblWinner.setHorizontalAlignment(JLabel.CENTER);
        JPanel finalPanel = (JPanel) cardPanel.getComponent(2);
        finalPanel.removeAll();
        finalPanel.add(lblWinner);
        cardLayout.show(cardPanel, "Final");
    }

    // Panel para el final de la partida
    private JPanel createFinalPanel() {
        JPanel finalPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Images/Wallpaper/final.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        finalPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        JLabel lblTitle = new JLabel("Fin de la Partida");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 90));
        lblTitle.setForeground(Color.WHITE);
        finalPanel.add(lblTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        JButton btnNewGame = new JButton("Nuevo Juego");
        btnNewGame.setPreferredSize(new Dimension(200, 60));
        finalPanel.add(btnNewGame, gbc);

        gbc.gridy = 2;
        JButton btnExit = new JButton("Salir");
        btnExit.setPreferredSize(new Dimension(200, 60));
        finalPanel.add(btnExit, gbc);

        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Selection");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return finalPanel;
    }

    private void updateButtonVisibility() {
        btnAttack1Player1.setEnabled(isPlayer1Turn);
        btnDefendPlayer1.setEnabled(isPlayer1Turn && defenseCooldownPlayer2 == 0);
        btnUltimatePlayer1.setEnabled(isPlayer1Turn);
        btnHealPlayer1.setEnabled(isPlayer1Turn);
        btnAttack1Player2.setEnabled(!isPlayer1Turn);
        btnDefendPlayer2.setEnabled(!isPlayer1Turn && defenseCooldownPlayer1 == 0);
        btnUltimatePlayer2.setEnabled(!isPlayer1Turn);
        btnHealPlayer2.setEnabled(!isPlayer1Turn);
    }

    private void switchTurns() {
        if (defenseCooldownPlayer1 > 0) {
            defenseCooldownPlayer1--;
        }
        if (defenseCooldownPlayer2 > 0) {
            defenseCooldownPlayer2--;
        }

        isPlayer1Turn = !isPlayer1Turn;
        updateButtonVisibility();
        if (isPlayer1Turn) {
            messageTextArea.append("Turno del Jugador 1\n");
        } else {
            messageTextArea.append("Turno del Jugador 2\n");
        }
    }

    private void resetGame() {
        player1Pokemon = null;
        player2Pokemon = null;
        isPlayer1Turn = false;
        defenseCooldownPlayer1 = 0;
        defenseCooldownPlayer2 = 0;
        messageTextArea.setText("");
        // Restablecer otras variables según sea necesario
    }

    private void startGame() {
        JPanel gameplayPanel = createGameplayPanel();
        JPanel gameContainerPanel = createGameContainerPanel(gameplayPanel);
        cardLayout.show(cardPanel, "Gameplay");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
}