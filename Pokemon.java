import javax.swing.ImageIcon;

public class Pokemon {
    private String name;
    private int level;
    private int hp;
    private ImageIcon sprite; // Imagen del pokemon

    // Constructor
    public Pokemon(String name, int level, int hp, String spritePath) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.sprite = new ImageIcon(spritePath);
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

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }

    public void attack() {
        System.out.println(getName() + " Atacó!");
    }
}
