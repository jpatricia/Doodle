import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class Canvas extends JPanel implements Observer {
    private Model model;

    public Canvas(Model model_) {
        model = model_;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {

                System.out.println("mouse pressed");
//                System.out.println("curColor1: "+model.curColor);
//                System.out.println("curStroke1: "+model.curStroke);

                //add color and stroke to table
                model.addStroke(model.curStroke);
                model.addColor(model.curColor);

                //add point to table and mark as start of line
                model.addPoints(me.getPoint());
                model.addCheckStart(me.getPoint());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                //add point to the table
                model.addPoints(me.getPoint());
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("mouse released------------");
                model.addTick();
                model.markEndLine();
            }
        });

        this.setBackground(Color.WHITE);
        System.out.println("Canvas");
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2_ = (Graphics2D) g;
        int i = 0;

        ArrayList<Point> points = new ArrayList<Point>();
        ArrayList<Point> hm = new ArrayList<Point>();
        ArrayList<Color> colorTable = new ArrayList<Color>();
        ArrayList<Float> strokeTable = new ArrayList<Float>();

        points = model.getPointsList();
        hm = model.getCheckStart();
        colorTable = model.getColor();
        strokeTable = model.getStroke();


        g2_.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (int j = 0; j <= points.size() - 2; j++) {
            Point p1 = points.get(j);
            Point p2 = points.get(j + 1);
            g2_.setStroke(new BasicStroke(strokeTable.get(i)));
            g2_.setPaint(colorTable.get(i));
            if (hm.contains(p2)) { //p2 is a new line
                i++;
            } else {
                g2_.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        System.out.println("update canvas");
        repaint();
    }

}
