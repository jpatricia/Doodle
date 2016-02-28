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
    private int counter1=0;
    boolean newLine = false; //to see if it's on the maximum tick of jslider or not
    boolean playforward = true;
    Boolean chooseCol = false;
    Boolean colorCustom = false;
    double scaleX=1.0,scaleY=1.0;
    Boolean fitMenu = false; //default is full screen size
 //   Draw dr;
    //JPanel p;
    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Point> hm = new ArrayList<Point>();
    public ArrayList<Color> colorTable = new ArrayList<Color>();
    public ArrayList<Float> strokeTable = new ArrayList<Float>();
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
        counter1 = completePoints.size()-1;
    }

    public void createNew(){
        System.out.println("CLEAR");
        points.clear();
        completePoints.clear();
        hm.clear();
        colorTable.clear();
        strokeTable.clear();
        endLineIndex.clear();
        curSliderValue = 100;
        newLine = false;

        setChanged();
        notifyObservers();
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

        chooseCol = true;
        setChanged();
        notifyObservers();
    }

    public void chooseColDone(){
        chooseCol = false;
        colorCustom = false;
    }

    public void addColor(Color newColor){
        //this method will store the current color information
        //System.out.println("addColor: "+newColor);
        colorTable.add(newColor);
    }

    public void addStroke(BasicStroke newStroke){
        //this method will store the current stroke thickness information
       // System.out.println("addStroke: "+newStroke);
        strokeTable.add(newStroke.getLineWidth());
    }

    public void addPoints(Point newPoint){
        //this method will add points to array when drawing
        //System.out.println("addPoints: "+newPoint);

        if(points.size()< completePoints.size()){
            System.out.println("------ draw in between ------");

            //change completePoints to be the same as points
            for(int i=completePoints.size()-1;i>=points.size();i--){
                completePoints.remove(i);
            }

        }

        points.add(newPoint);
        completePoints.add(newPoint);

        setChanged();
        notifyObservers();

    }

    public void addCheckStart(Point newPoint){
        //this has the point of the start of a new line
       // System.out.println("addCheckStart: "+newPoint);
        hm.add(newPoint);
    }

    public void markEndLine(){
        //This has the index of the end of each line at completePoints array
        endLineIndex.add(points.size()-1);
        newLine = true;
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

    public ArrayList getCompletePoints(){ return completePoints;}

    public ArrayList getEndLineIndex(){ return endLineIndex;}

    public void setModel(ArrayList<Point> point, ArrayList<Point> hashmap,
                         ArrayList<Color> colortable_,ArrayList<Float> stroketable_,
                         ArrayList<Point> completePoint,ArrayList<Integer> endIndex){

        points.clear();
        hm.clear();
        colorTable.clear();
        strokeTable.clear();
        completePoints.clear();
        endLineIndex.clear();


        for(int i=0;i<point.size();i++){
            points.add(point.get(i));
        }

        for(int i=0;i<hashmap.size();i++){
            hm.add(hashmap.get(i));
        }

        for(int i=0;i<colortable_.size();i++){
            colorTable.add(colortable_.get(i));
        }

        for(int i=0;i<stroketable_.size();i++){
            strokeTable.add(stroketable_.get(i));
        }

        for(int i=0;i<completePoint.size();i++){
            completePoints.add(completePoint.get(i));
        }

        for(int i=0;i<endIndex.size();i++){
            endLineIndex.add(endIndex.get(i));
        }

        setChanged();
        notifyObservers();

    }

    public void addTick(){
        System.out.println("addtick function");
        System.out.println("maxTick in newline fn: "+newLine);
        setChanged();
        notifyObservers();
    }

//    public void SetFit(){
//        System.out.println("set fit");
//        fitMenu = true;
//    }

    public void fit(int width, int height){
        //width and height are the width and height of the window

        System.out.println("window width: "+width);
        System.out.println("window height: "+height);

        fitMenu = true;

        if(width !=800){
            scaleX = ((double)width)/800;
        }
        if(height!=600){
            scaleY = ((double)height)/600;
        }

        System.out.println("scaleX: "+scaleX);
        System.out.println("scaleY: "+scaleY);

        setChanged();
        notifyObservers();

    }

    public void full(){
        System.out.println("set full");
        fitMenu = false;
        scaleX = 1.0;
        scaleY = 1.0;

        setChanged();
        notifyObservers();
    }

    public void getDrawing(int sliderValue){
        System.out.println("sliderValue: "+sliderValue);

        int space;
        int lineNum;
        int i=0;
//        if(hm.size()!=0){
            space = 100/hm.size();
            lineNum = sliderValue/space;
//        }else{
//            space =0;
//            lineNum = 0;
//        }

        System.out.println("space get drawing: "+space);
        System.out.println("lineNum get drawing: "+lineNum);
        System.out.println("hm.size get drawing: "+hm.size());

        if(sliderValue != 100){
            System.out.println("getDrawing slidervalue not max");
            newLine = false;
        }

//        if(lineNum < hm.size()) {
//            System.out.println("maxtick is false");
//            maxTick = false;
//        }
//        else {
//            System.out.println("maxtick is true");
//            maxTick=true;
//        }


        if(sliderValue%space ==0 || sliderValue ==100){
            points.clear();
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
            if(tickIndex>points.size()){
                //move forward
                i=points.size();
                System.out.println("FORWARD");
                while(i <= tickIndex) {
                    points.add(completePoints.get(i));
                    i++;
                }
            }else{
                //move back or rewind
                System.out.println("REWIND");
                int j = points.size()-1;
                while(j>=tickIndex){
                    points.remove(j);
                    j--;
                }

            }

        }


        setChanged();
        notifyObservers();

    }

    ActionListener timerListener = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            System.out.println("timer action performed");

            if(playforward){
                if(counter < completePoints.size()){
                    System.out.println("counter: "+counter);
                    points.add(completePoints.get(counter));
                    counter++;
                }else{
                    timer.stop();
                }
            }else{
                if(counter1 >= 0){
                    points.remove(counter1);
                    counter1--;
                }else{
                    timer.stop();
                    for(int i=0;i<completePoints.size();i++){
                        points.add(completePoints.get(i));
                    }
                }
            }

            setChanged();
            notifyObservers();
        }};

}
