public class Charizard extends Pokemon{

    public Charizard(){
        super("Charizard", 5, 10, "Images/Charizard/charizard.gif");
    }

    public void attack() {
        System.out.println(getName() + " uso ...!");
    }
}
