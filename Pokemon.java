import javax.swing.ImageIcon;

public class Pokemon {
    private String name;
    private int level;
    private int hp;
    private ImageIcon sprite; // Imagen del pokemon
    private int critDmg;
    private int critRate;

    // Constructor
    public Pokemon(String name, int level, int hp, String spritePath, int critDmg, int critRate) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.sprite = new ImageIcon(spritePath);
        this.critDmg = critDmg;
        this.critRate = critRate;
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

    public int getCritDmg() {
        return critDmg;
    }

    public void setCritDmg(int critDmg) {
        this.critDmg = critDmg;
    }

    public int getCritRate() {
        return critRate;
    }
    
    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }
}
