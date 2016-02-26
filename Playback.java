import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Playback extends JPanel implements Observer{

    private Model model;
    private JSlider slider;
    private JButton Play;
    private JButton End;
    private JButton Start;

    public Playback(Model model_){
        model = model_;

//        this.setLayout(new FlowLayout());

        slider = new JSlider();
        slider.setValue(0);
        slider.addChangeListener(new BoundedChangeListener());

        Play = new JButton("Play");
        End = new JButton("End");
        Start = new JButton("Start");

        Play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.points.clear(); //clear the table of points
                System.out.println("size of points: "+model.points.size());
                model.start(); //start the timer
              //  slider.setValue(0);
            }
        });

        addComponents();

        this.setBackground(Color.LIGHT_GRAY);
        System.out.println("Playback");
    }

    void addComponents(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //add play button
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        this.add(Play,gc);
        gc.gridx++;

        //add slider
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0.2;
        gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(slider, gc);
        gc.gridx++;

        //gc.fill = GridBagConstraints.NONE;

        //add start button
        gc.anchor = GridBagConstraints.EAST;
        this.add(Start, gc);
        gc.gridx++;

        //add End button
        gc.anchor = GridBagConstraints.EAST;
        this.add(End, gc);
    }

    @Override
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        System.out.println("in here playback paintcomponent: "+model.hm.size());
        if(model.hm.size()!=0){
            System.out.println("here");
            int mult = model.hm.size();
            int tickspace = 100/mult;
            slider.setMaximum(mult*tickspace);
            slider.setMajorTickSpacing(tickspace);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            if(model.maxTick){
                slider.setValue(mult*tickspace);
            }
        }

    }

    class BoundedChangeListener implements ChangeListener{

        public void stateChanged(ChangeEvent e) {
            Object obj = e.getSource();
            if(obj instanceof BoundedRangeModel){
                BoundedRangeModel range = (BoundedRangeModel) obj;
                if(!range.getValueIsAdjusting()){
                    System.out.println("Changed: "+ range.getValue());
                }
            }else if(obj instanceof JSlider){
                JSlider jslider = (JSlider) obj;
                if(!jslider.getValueIsAdjusting()){
                    model.getDrawing(jslider.getValue());
                    System.out.println("Slider changed: "+jslider.getValue());
                }
            }else{
                System.out.println("Something changed");
            }

        }
    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
       System.out.println("update Playback");
        this.repaint();
    }
}
