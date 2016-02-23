import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
    Canvas c;
    Palette pal;
    Playback pc;
    Color curColor;
    BasicStroke curStroke;
 //   Draw dr;
    //JPanel p;
    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Point> hm = new ArrayList<Point>();
    public ArrayList<Color> colorTable = new ArrayList<Color>();
    public ArrayList<BasicStroke> strokeTable = new ArrayList<BasicStroke>();

    public Model(){
        c = new Canvas(this);
        pal = new Palette(this);
        pc = new Playback(this);
        curColor = Color.BLACK; //default
        curStroke = new BasicStroke(1);
//        p = new JPanel();
//        p.setLayout(new BorderLayout());
//        c = new Canvas(this,p);
//        pal = new Palette(this,p);
//        pc = new Playback(this,p);
    }

//    public void addCanvas(JPanel p_){
//        p=p_;
//        //add canvas
//        p.add(c, BorderLayout.CENTER);
//        p.setOpaque(true);
//    }
//
//    public void addPalette(JPanel p_){
//        p=p_;
//        //add palette
//        p.add(pal, BorderLayout.WEST);
//        p.setOpaque(true);
//    }
//
//    public void addPlayback(JPanel p_){
//        p=p_;
//        //add playback
//        p.add(pc, BorderLayout.SOUTH);
//        p.setOpaque(true);
//    }
//
//    public void addDraw(JPanel p_,JPanel can){
//        System.out.println("addDraw");
//        p=p_;
//        dr = new Draw(can,Color.BLACK,new BasicStroke(1),this);
//        dr.setBackground(Color.WHITE);
//        dr.setOpaque(true);
//        p.add(dr);
//        p.setOpaque(true);
//    }

//    public void addColorPalette(JPanel p_,JPanel pt){
//        p=p_;
//        p.add(pt, BorderLayout.NORTH);
//        p.setOpaque(true);
//    }
//
//    public void addStrokePalette(JPanel p_,JPanel pb){
//        p=p_;
//        p.add(pb, BorderLayout.SOUTH);
//        p.setOpaque(true);
//    }

    public void changeStroke(BasicStroke st){
        System.out.println("changeStroke");
        curStroke = st;
        System.out.println("curStroke: "+curStroke.getLineWidth());
    }

    public void changeColor(Color col){
        System.out.println("changeColor");
        curColor = col;
        System.out.println("curColor: "+curColor);
    }

    public void colorChooser(){
        System.out.println("Color chooser");
        Color colorChoose = Color.BLACK;
        colorChoose = JColorChooser.showDialog(c,"Pick a Color",colorChoose);
        if(colorChoose == null) colorChoose = Color.BLACK;
        curColor = colorChoose;
        System.out.println("curColor: "+curColor);
//        p.setOpaque(true);
    }

    public void addColor(Color newColor){
        System.out.println("addColor: "+newColor);
        colorTable.add(newColor);
    }

    public void addStroke(BasicStroke newStroke){
        System.out.println("addStroke: "+newStroke);
        strokeTable.add(newStroke);
    }

    public void addPoints(Point newPoint){
        System.out.println("addPoints: "+newPoint);
        points.add(newPoint);
    }

    public void addCheckStart(Point newPoint){
        System.out.println("addCheckStart: "+newPoint);
        hm.add(newPoint);
    }

    public ArrayList getPointsList(){
        return points;
    }

    public ArrayList getColor(){
        return colorTable;
    }

    public ArrayList getStroke(){
        return strokeTable;
    }

    public ArrayList getCheckStart(){
        return hm;
    }

}
