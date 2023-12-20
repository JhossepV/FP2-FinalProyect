import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Bulbasaur extends Pokemon {
    
    private ArrayList<Image> animationFrames;
    private int currentFrameIndex;

    public Bulbasaur() {
        super("Bulbasaur", 5, 100);
        loadAnimationFrames();
    }
    
    //Idle Animation
    private void loadAnimationFrames(){
        animationFrames = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            String frameFileName = "FP2-FinalProyect/Images/Bulbasaur/bulbasaur"+i+".png";
            Image frame = new ImageIcon(frameFileName).getImage();
            animationFrames.add(frame);
        }
    }

    public Image getCurrentAnimationFrame(){
        return animationFrames.get(currentFrameIndex);
    }

    public void playAnimation(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                currentFrameIndex = (currentFrameIndex+1)%animationFrames.size();
                //System.out.println("Frame index: "+currentFrameIndex);
            }
        }, 0, 200);
    }

    @Override
    public void attack() {
        System.out.println(getName() + " useo Latigo Cepa!");
    }
}