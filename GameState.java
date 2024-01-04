import java.io.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private Pokemon player1Pokemon;
    private Pokemon player2Pokemon;
    private boolean isPlayer1Turn;
    private int defenseCooldownPlayer1;
    private int defenseCooldownPlayer2;

    // Otros campos necesarios

    public GameState(Pokemon player1Pokemon, Pokemon player2Pokemon, boolean isPlayer1Turn,
        int defenseCooldownPlayer1, int defenseCooldownPlayer2) {
        this.player1Pokemon = player1Pokemon;
        this.player2Pokemon = player2Pokemon;
        this.isPlayer1Turn = isPlayer1Turn;
        this.defenseCooldownPlayer1 = defenseCooldownPlayer1;
        this.defenseCooldownPlayer2 = defenseCooldownPlayer2;
    }

    public Pokemon getPlayer1Pokemon() {
        return player1Pokemon;
    }

    public Pokemon getPlayer2Pokemon() {
        return player2Pokemon;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public int getDefenseCooldownPlayer1() {
        return defenseCooldownPlayer1;
    }

    public int getDefenseCooldownPlayer2() {
        return defenseCooldownPlayer2;
    }

}
