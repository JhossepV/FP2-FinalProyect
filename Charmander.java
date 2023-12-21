public class Charmander extends Pokemon {

    public Charmander() {
        super("Charmander", 5, 100, "");
    }

    @Override
    public void attack() {
        System.out.println(getName() + " uso Lanzallamas!");
    }

}