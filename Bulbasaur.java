public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur", 5, 100);
    }

    @Override
    public void attack() {
        System.out.println(getName() + " useo Latigo Cepa!");
    }

}