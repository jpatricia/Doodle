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
    public JSlider slider;
    private JButton Play;
    private JButton End;
    private JButton Start;
    private JButton Rewind;

    public Playback(Model model_){
        model = model_;

//        this.setLayout(new FlowLayout());

        slider = new JSlider();
        slider.setValue(0);
        slider.setMaximum(0);
        slider.addChangeListener(new BoundedChangeListener());

        Play = new JButton("Play");
        End = new JButton("End");
        Start = new JButton("Start");
        Rewind = new JButton("Playback");

        Play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.points.clear(); //clear the table of points
                System.out.println("size of points: "+model.points.size());
                model.playforward = true;
                model.start(); //start the timer
              //  slider.setValue(0);
            }
        });

        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("start button");
                slider.setValue(0);
                model.curSliderValue = 0;
            }
        });

        End.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("end button");
                slider.setValue(100);
                model.curSliderValue = 100;
            }
        });

        Rewind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Playback button");
                model.playforward = false;
                model.start();
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

        //add playback button
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.WEST;
        this.add(Rewind,gc);
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
        System.out.println("playback paintcomponent: "+model.hm.size());
        if(model.hm.size()!=0) {
            System.out.println("setting up tick hm size!=0 playback paint");

            int mult = model.hm.size();
            int tickspace = 100 / mult;

            System.out.println("mult: " + mult);
            System.out.println("tickspace: " + tickspace);

            slider.setMaximum(100);
            slider.setMajorTickSpacing(tickspace);
            if(model.newLine || model.hm.size()==1){
                System.out.println("playback paint newline");
                slider.setValue(100);
            }
            // slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
//            System.out.println("maxTick: "+model.maxTick);
//            if(model.maxTick){
//                slider.setValue(100);
//                model.curSliderValue = 100;
//            }
        }
        else{
            System.out.println("playback paint hm size=0");
            slider.setMajorTickSpacing(0);
            slider.setValue(0);
            //slider.setPaintTicks(false);
        }


    }

    class BoundedChangeListener implements ChangeListener{

        public void stateChanged(ChangeEvent e) {
            Object obj = e.getSource();
            if(obj instanceof JSlider){
                JSlider jslider = (JSlider) obj;
                if(!jslider.getValueIsAdjusting()){
                    if(model.hm.size()!=0){
                        model.getDrawing(jslider.getValue());
                        model.curSliderValue = jslider.getValue();
                        System.out.println("Slider changed: "+jslider.getValue());
                    }

                }
            }

        }
    }

    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
       System.out.println("update Playback");
        this.repaint();
    }
}
