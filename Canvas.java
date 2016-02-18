import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class Canvas extends JPanel implements Observer{
    private Model model;
    private JFrame f;

    public Canvas(Model model_){
        model=model_;

        this.setBackground(Color.WHITE);
        System.out.println("Canvas");
    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        repaint();
    }
}
