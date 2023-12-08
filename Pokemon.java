public class Pokemon {

    // Atributos de los Pokemones, el Image es por una idea que vi, capaz funciona para los sprites de los pokemos, pero aun no se xD
    String name;
    int level; 
    int hp;
    Image sprite;
    
    // Constructor
    public Pokemon(String name, int level, int hp, Image sprite) {
        this.name = name;
        this.level = level;
        this.hp = hp; 
        this.sprite = sprite;
    }

    // Getters y setters
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public Image getSprite() {
        return sprite; 
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
    
}