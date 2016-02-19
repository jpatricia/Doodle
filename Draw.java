import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Draw extends JPanel{
    //x1,y1 is the old coordinate
    //x2,y2 is the current coordinate
    //Image img;
    private Graphics2D g2;
    private JPanel p;
    int x1,y1,x2,y2;
    public BufferedImage img;
    public BasicStroke stroke;

    public Draw(JPanel c,Color color_,BasicStroke stroke_){ //constructor
        p = c;
        stroke = stroke_;
        System.out.println("Draw Constructor");
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                x1 = me.getX();
                y1 = me.getY();

                g2.setStroke(stroke);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent me){
                x2 = me.getX();
                y2 = me.getY();
                if(g2 !=null){
                    g2.drawLine(x1,y1,x2,y2);
                }
                repaint();
                x1 = x2;
                y1 = y2;
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
        int w = p.getWidth();
        int h = p.getHeight();
//        System.out.println("p.getWidth: "+w);
//        System.out.println("p.getHeight: "+h);

        if (img == null) {
            System.out.println("null fcn");
            // img = createImage(w,h);
            img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            g2 = (Graphics2D) img.getGraphics();
            g2.setPaint(Color.BLACK); //default
            if (stroke == null) stroke = new BasicStroke(1);
            g2.setStroke(stroke);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            System.out.println("not null");
            g2 = (Graphics2D) img.getGraphics();
            g2.setPaint(Color.BLACK); //default
            System.out.println("ttttttt"+stroke.getLineWidth());
            g2.setStroke(stroke);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.drawImage(img, 0, 0, null);
    }

    public void changeStroke(BasicStroke st){
        System.out.println("changeStroke");
        stroke = st;
        System.out.println(stroke.getLineWidth());
        repaint();
       // stroke = new BasicStroke(1);
//       repaint();
    }
}
