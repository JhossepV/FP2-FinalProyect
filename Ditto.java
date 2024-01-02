public class Ditto extends Pokemon{
    
    public Ditto(){
        super("Ditto", 5, 10, "Images/Charizard/Ditto.gif");
    }

    public void attack() {
        System.out.println(getName() + " uso ...!");
    }
}
