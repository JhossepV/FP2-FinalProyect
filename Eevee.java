public class Eevee extends Pokemon{

    public Eevee(){
        super("Eevee", 5, 10, "Images/Charizard/Eevee.gif", 100, 50);
    }

    public void attack() {
        System.out.println(getName() + " uso ...!");
    }
    
}
