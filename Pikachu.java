public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", 5, 100, "Images/Bulbasaur/bulbasaur.gif");
    }

    public void attack() {
        System.out.println(getName() + " uso Impact Trueno!");
    }

}