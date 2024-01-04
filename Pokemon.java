import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private static final int MAX_HP = 100;
    private String name;
    private int level;
    private int hp;
    private boolean isDefending;
    private int lastHealing;
    private ImageIcon sprite;
    private List<Attack> attacks;

    public Pokemon(String name, int level, int hp, String spritePath) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.isDefending = false;
        this.sprite = new ImageIcon(spritePath);
        this.attacks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
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

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public void learnAttack(String attackName, int damage) {
        Attack attack = new Attack(attackName, damage);
        attacks.add(attack);
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void attack(Pokemon target, Attack chosenAttack) {
        System.out.println(name + " utilizó " + chosenAttack.getName() + " contra " + target.getName());

        if (isDefending) {
            // Aplicar defensa solo para el próximo ataque
            int defendedDamage = (int) Math.round(chosenAttack.getDamage() * 0.35);
            target.takeDamage(defendedDamage);
            isDefending = false;  // Desactivar la defensa después de usarla
        } else {
            int damage = calculateDamage(chosenAttack, target);
            target.takeDamage(damage);
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage);
        hp = Math.max(0, hp - actualDamage);

        System.out.println(name + " recibió " + actualDamage + " de daño. HP restante: " + hp);
    }

    public void heal() {
        hp = Math.min(hp + 20, MAX_HP);
        System.out.println(name + " se curó por " + "20" + " HP. HP restante: " + hp);
    }

    public void defend() {
        if (!isDefending) {
            isDefending = true;
            System.out.println("El próximo ataque enemigo será defendido.");
        } else {
            System.out.println(name + " ya está defendiendo.");
        }
    }

    private int calculateDamage(Attack chosenAttack, Pokemon target) {
        int baseDamage = chosenAttack.getDamage();
        int calculatedDamage = baseDamage + (level / 2);

        int actualDamage = Math.max(0, calculatedDamage);

        return actualDamage;
    }

    public int getBasicDamage() {
        if (name.equals("Pikachu")) {
            return 20;
        } else if (name.equals("Bulbasaur")) {
            return 15;
        } else if (name.equals("Charmander")) {
            return 25;
        } else if (name.equals("Squirtle")) {
            return 30;
        }
        return 0;
    }

    public int getUltimateDamage() {
        if (name.equals("Pikachu")) {
            return (int) (hp * 2 / 5);
        } else if (name.equals("Bulbasaur")) {
            return 30;
        } else if (name.equals("Charmander")) {
            return 50;
        } else if (name.equals("Squirtle")) {
            return 45;
        }
        return 0;
    }

    public double getHpPercentage() {
        return (double) hp / MAX_HP;
    }

    public void updateHealthBar(JProgressBar healthBar) {
        healthBar.setValue((int) (getHpPercentage() * 100));
    }
}