import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class Canvas extends JPanel implements Observer{
    private Model model;

    public Canvas(Model model_,JPanel view){
        System.out.println("Canvas");
        model = model_;
        JPanel p = new JPanel();
    //    Draw draw = new Draw(this, Color.BLACK,new BasicStroke(1));

    //    draw.setBackground(Color.WHITE);

        p.setLayout(new BorderLayout());

        p.setBackground(Color.WHITE);
     //   draw.setOpaque(true);
        p.setOpaque(true);
        model.addDraw(p,this);
        //p.add(draw);

        this.add(p);
        this.setBackground(Color.WHITE);

    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        repaint();
    }
}
