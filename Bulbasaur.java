import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Image;

public class Bulbasaur extends Pokemon {
    
    private ArrayList<Image> animationFrames;
    private int currentFrameIndex;
    public Bulbasaur() {
        super("Bulbasaur", 5, 100);
        loadAnimationFrames();
    }

    @Override
    public void attack() {
        System.out.println(getName() + " useo Latigo Cepa!");
    }
    private void loadAnimationFrames(){
        animationFrames = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            String frameFileName = "Images/bulbasaur"+i+".gif";
            Image frame = new ImageIcon(frameFileName).getImage();
            animationFrames.add(frame);
        }

    }

}