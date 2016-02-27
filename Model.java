import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
    Canvas c;
    Palette pal;
    Playback pc;
    Color curColor;
    BasicStroke curStroke;
    Timer timer;
    int curSliderValue=100; //for play button timer
    private int counter=0;
    boolean maxTick = true; //to see if it's on the maximum tick of jslider or not
 //   Boolean chooseCol = false;
 //   Draw dr;
    //JPanel p;
    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Point> hm = new ArrayList<Point>();
    public ArrayList<Color> colorTable = new ArrayList<Color>();
    public ArrayList<BasicStroke> strokeTable = new ArrayList<BasicStroke>();
    public ArrayList<Point> completePoints = new ArrayList<Point>();
    public ArrayList<Integer> endLineIndex = new ArrayList<Integer>();

    public Model(){
        c = new Canvas(this);
        pal = new Palette(this);
        pc = new Playback(this);
        curColor = Color.BLACK; //default
        curStroke = new BasicStroke(1);
        setChanged();
        timer = new Timer((15),timerListener);
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

    public void start(){
        //start the timer
        System.out.println("///////Start timer///////");
        timer.start();
        counter = 0;
    }

    public void changeStroke(BasicStroke st){
        //this method sets the stroke based on the button clicked
        System.out.println("changeStroke");
        curStroke = st;
        System.out.println("curStroke: "+curStroke.getLineWidth());
    }

    public void changeColor(Color col){
        //this method sets the value of current color based on button clicked
        System.out.println("changeColor");
        curColor = col;
        System.out.println("curColor: "+curColor);
    }

    public void colorChooser(){
        //this method is for the color choose dialog
        System.out.println("Color chooser");
        Color colorChoose = Color.BLACK;
        colorChoose = JColorChooser.showDialog(c,"Pick a Color",colorChoose);
        if(colorChoose == null) colorChoose = Color.BLACK;
        curColor = colorChoose;
        System.out.println("curColor: "+curColor);
//        p.setOpaque(true);

//        chooseCol = true;
//        setChanged();
//        notifyObservers();
    }

    public void addColor(Color newColor){
        //this method will store the current color information
        //System.out.println("addColor: "+newColor);
        colorTable.add(newColor);
    }

    public void addStroke(BasicStroke newStroke){
        //this method will store the current stroke thickness information
       // System.out.println("addStroke: "+newStroke);
        strokeTable.add(newStroke);
    }

    public void addPoints(Point newPoint){
        //this method will add points to array when drawing
        //System.out.println("addPoints: "+newPoint);

        points.add(newPoint);
        completePoints.add(newPoint);

    }

    public void addCheckStart(Point newPoint){
        //this has the point of the start of a new line
       // System.out.println("addCheckStart: "+newPoint);
        hm.add(newPoint);
    }

    public void markEndLine(){
        //This has the index of the end of each line at completePoints array
        endLineIndex.add(points.size()-1);
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

    public void addTick(){
        System.out.println("addtick function");
        setChanged();
        notifyObservers();
    }

    public void getDrawing(int sliderValue){
        System.out.println("sliderValue: "+sliderValue);

        points.clear();
        int space = 100/hm.size();
        int lineNum = sliderValue/space;
        int i=0;


        if(lineNum < hm.size()) maxTick = false;
        else maxTick=true;

        System.out.println("space: "+space);
        System.out.println("lineNum: "+lineNum);

        if(sliderValue%space ==0 || sliderValue ==100){
            //the arrow is on the tick
            System.out.println("arrow is on the tick");

            if(lineNum>0 && lineNum <hm.size()){
                System.out.println("lineNum not 0 and max: "+lineNum);
                while(i < endLineIndex.get(lineNum-1)){
                    points.add(completePoints.get(i));
                    i++;
                }
                //Point endLine = hm.get(lineNum);
                //System.out.println("endLine: "+endLine);

//            while(completePoints.get(i) != endLine){
//                points.add(completePoints.get(i));
//                i++;
//            }

//            for(int i=0;i<completePoints.size();i++){
//                if(completePoints.get(i) != endLine){
//                    points.add(completePoints.get(i));
//                }
//            }
            }else if (lineNum==0){
                System.out.println("lineNum is zero");
            }
            else if(lineNum == hm.size() || sliderValue ==100){
                System.out.println("lineNum is maximum: "+hm.size());
                for(int j=0;j<completePoints.size();j++){
                    points.add(completePoints.get(j));
                }

            }

        }else{
            //the arrow is between two ticks
            System.out.println("arrow is in the middle of two ticks");
            int NumOfPoints;
            int tickIndex;
            int remainder;

            if(sliderValue<space){
                NumOfPoints = endLineIndex.get(0);
                tickIndex = (NumOfPoints*sliderValue)/space;
                remainder = 0;
            }else{
                NumOfPoints = endLineIndex.get(lineNum) - endLineIndex.get(lineNum-1);
                remainder = sliderValue%space;
                tickIndex = endLineIndex.get(lineNum-1)+(NumOfPoints*remainder)/space;
            }

            System.out.println("# of points: "+NumOfPoints);
            System.out.println("remainder: "+remainder);
            System.out.println("tickIndex: "+tickIndex);
            while(i < tickIndex) {
                points.add(completePoints.get(i));
                i++;
            }
        }


        setChanged();
        notifyObservers();

    }

    ActionListener timerListener = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            System.out.println("timer action performed");

            if(counter < completePoints.size()){
                System.out.println("counter: "+counter);
                points.add(completePoints.get(counter));
                counter++;
            }else{
                timer.stop();
            }
            setChanged();
            notifyObservers();
        }};

}
