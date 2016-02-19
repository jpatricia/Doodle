import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Draw extends JPanel{
    //x1,y1 is the old coordinate
    //x2,y2 is the current coordinate
    //Image img;
  //  private Graphics2D g2;
    //  public BufferedImage img;
    private JPanel p;
    int x1,y1,x2,y2,x3,y3,index=0;
    public Color color;
    public BasicStroke stroke;
    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Point> hm = new ArrayList<Point>();
    public ArrayList<Color> colorTable = new ArrayList<Color>();
    public ArrayList<BasicStroke> strokeTable = new ArrayList<BasicStroke>();

    public Draw(JPanel c,Color color_,BasicStroke stroke_){ //constructor
        p = c;
        color = color_;
        stroke = stroke_;

        System.out.println("Draw Constructor");
        System.out.println("stroke: "+stroke.getLineWidth());
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                x1 = me.getX();
                y1 = me.getY();

                x2 = x1;
                y2 = y1;

                strokeTable.add(stroke);
                colorTable.add(color);

                points.add(me.getPoint());
                hm.add(me.getPoint());
               // repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
               // repaint();

              //  hm.put(me.getPoint(), new Boolean(true));
              //  indexEnd.add(points.size()-1);
                // index = 0;

            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent me){
                points.add(me.getPoint());
                repaint();

//                Graphics g = getGraphics();
//                x2 = me.getX();
//                y2 = me.getY();
//                x2 = me.getX();
//                y2 = me.getY();
//                if(g2 !=null){
//                    g2.drawLine(x1,y1,x2,y2);
//                }
//                repaint();
//                x1 = x2;
//                y1 = y2;
            }
        });
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(p.getSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(p.getSize());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2_ = (Graphics2D)g;
        int i=0;

        g2_.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for(int j=0;j<=points.size()-2;j++) {
            Point p1 = points.get(j);
            Point p2 = points.get(j + 1);
            g2_.setStroke(strokeTable.get(i));
            g2_.setPaint(colorTable.get(i));
            if(hm.contains(p2)){ //p2 is a new line
                i++;
            }else{
                g2_.drawLine(p1.x,p1.y,p2.x,p2.y);
            }
        }

//        if(startPoint.size()!=0) {
//            for (int i = 0; i < startPoint.size(); i++) {
//                g2_.setStroke(strokeTable.get(i));
//                g2_.setPaint(colorTable.get(i));
//                for (int j = index; j < startPoint.get(i) - 2; j++) {
//                    Point p1 = points.get(j);
//                    Point p2 = points.get(j + 1);
//                    g2_.drawLine(p1.x, p1.y, p2.x, p2.y);
//                }
//                index = startPoint.get(i);
//            }
//        }

//        if(indexEnd.size()!=0){
//            for(int i=0;i<indexEnd.size();i++) {
//                g2_.setStroke(strokeTable.get(i));
//                g2_.setPaint(colorTable.get(i));
//                for (int j = index; j < indexEnd.get(i) - 1; j++) {
//                    Point p1 = points.get(j);
//                    Point p2 = points.get(j + 1);
//                    g2_.drawLine(p1.x, p1.y, p2.x, p2.y);
//                }
//                index = indexEnd.get(i) + 1;
//            }
//            }
//        }
//          else{
//            for(int j=0;j<points.size()-2;j++){
//                Point p1 = points.get(j);
//                Point p2 = points.get(j+1);
//                g2_.drawLine(p1.x,p1.y,p2.x,p2.y);
//
//            }
//          }


//        System.out.println("p.getWidth: "+w);
//        System.out.println("p.getHeight: "+h);

//        if (img == null) {
//            System.out.println("null fcn");
//            // img = createImage(w,h);
//            img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//            g2 = (Graphics2D) img.getGraphics();
//            g2.setPaint(Color.BLACK); //default
//            if (stroke == null) stroke = new BasicStroke(1);
//            g2.setStroke(stroke);
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        } else {
//            System.out.println("not null");
//            g2 = (Graphics2D) img.getGraphics();
//            g2.setPaint(Color.BLACK); //default
//            System.out.println("ttttttt"+stroke.getLineWidth());
//            g2.setStroke(stroke);
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        }
//        g.drawImage(img, 0, 0, null);
    }

//    public void changeStroke(BasicStroke st){
//        System.out.println("changeStroke");
//        stroke = st;
//        System.out.println(stroke.getLineWidth());
//    }
}
