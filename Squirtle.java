public class Squirtle extends Pokemon {

    public Squirtle() {
        super("Squirtle", 5, 100, "Images/Squirtle/Squirtle.gif");
    }

    public void attack() {
        System.out.println(getName() + " uso Cañon de Agua!");
    }

}