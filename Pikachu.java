import java.io.Serializable;

public class Pikachu extends Pokemon implements Serializable {
    public Pikachu() {
        super("Pikachu", 100, 100, "Images/Pikachu/pikachu.gif");
        learnAttack("Ataque Básico", 20);
    }
}
