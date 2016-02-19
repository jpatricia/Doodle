import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class Model extends Observable {
    Canvas c;
    Palette pal;
    Playback pc;
    Draw dr;
    JPanel p;

    public Model(){
        p = new JPanel();
        p.setLayout(new BorderLayout());
        c = new Canvas(this,p);
        pal = new Palette(this,p);
        pc = new Playback(this,p);
    }

    public void addCanvas(JPanel p_){
        p=p_;
        //add canvas
        p.add(c, BorderLayout.CENTER);
        p.setOpaque(true);
    }

    public void addPalette(JPanel p_){
        p=p_;
        //add palette
        p.add(pal, BorderLayout.WEST);
        p.setOpaque(true);
    }

    public void addPlayback(JPanel p_){
        p=p_;
        //add playback
        p.add(pc, BorderLayout.SOUTH);
        p.setOpaque(true);
    }

    public void addDraw(JPanel p_,JPanel can){
        System.out.println("addDraw");
        p=p_;
        dr = new Draw(can,Color.BLACK,new BasicStroke(1));
        dr.setBackground(Color.WHITE);
        dr.setOpaque(true);
        p.add(dr);
        p.setOpaque(true);
    }

    public void addColorPalette(JPanel p_,JPanel pt){
        p=p_;
        p.add(pt, BorderLayout.NORTH);
        p.setOpaque(true);
    }

    public void addStrokePalette(JPanel p_,JPanel pb){
        p=p_;
        p.add(pb, BorderLayout.SOUTH);
        p.setOpaque(true);
    }

    public void changeStroke(BasicStroke st){
        System.out.println("st: "+st.getLineWidth());
        dr.stroke = st;
        System.out.println("st: "+dr.stroke.getLineWidth());
    }
}
