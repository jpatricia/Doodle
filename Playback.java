import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Playback extends JPanel implements Observer{

    private Model model;
    private JFrame f;

    public Playback(Model model_,JPanel vw){
        model = model_;

//        this.setLayout(new FlowLayout());

        JSlider slider = new JSlider(0,100,0);

//        this.add(slider);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        this.add(new JButton("Play"),gc);
        gc.gridx++;

        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0.2;
        gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(slider, gc);
        gc.gridx++;

        //gc.fill = GridBagConstraints.NONE;

        gc.anchor = GridBagConstraints.EAST;
        this.add(new JButton("Start"), gc);
        gc.gridx++;

        gc.anchor = GridBagConstraints.EAST;
        this.add(new JButton("End"), gc);


        this.setBackground(Color.LIGHT_GRAY);
        System.out.println("Playback");
    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        //repaint();
    }
}
