import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI{
  public static void main(String[] args)throws IOException{
    LinkedList<Piece> ps=new LinkedList<>();
    /*BufferedImage all=ImageIO.read(new File("\"C:\\Users\\DELL\\Documents\\dev\\java\\WorkStation\\FP2-FinalProyect\\chess.png\""));
    Image imgs[]=new Image[12];
    int ind=0;
    for(int y=0;y<400;y+=200){
      for(int x=0;x<1200;x+=200){
        imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
        ind++;
      }    
    }*/
    JFrame frame=new JFrame();
    frame.setBounds(10, 10, 512, 512);
    frame.setUndecorated(true);
    JPanel pn=new JPanel(){
      @Override
      public void paint(Graphics g) {
        boolean white=true;
        for(int y=0; y<8; y++){
          for(int x=0; x<8; x++){
            if(white){
              g.setColor(Color.white.darker());
            }else{
              g.setColor(Color.black);
            }
            g.fillRect(x*64, y*64, 64, 64);
          white=!white;
          }
          white=!white;
        }
      }
    };
    frame.add(pn);
    frame.setDefaultCloseOperation(0);
    frame.setVisible(true);

  }
}