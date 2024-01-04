import java.io.Serializable;

public class Bulbasaur extends Pokemon implements Serializable  {
    public Bulbasaur() {
        super("Bulbasaur", 120, 120, "Images/Bulbasaur/bulbasaur.gif");
        learnAttack("Ataque Básico", 15);
    }
}
