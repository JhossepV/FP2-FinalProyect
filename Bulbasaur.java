public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur", 5, 100, "Images/Pikachu/pikachu.gif", 100, 50);
    }
    
    public void attack() {
        System.out.println(getName() + " useo Latigo Cepa!");
    }
}