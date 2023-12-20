public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", 5, 100);
    }

    @Override
    public void attack() {
        System.out.println(getName() + " uso Impact Trueno!");
    }

}