import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Palette extends JPanel implements Observer{

    private Model model;
    private JPanel pTop;
    private JPanel vi;
    private JPanel pBottom;
    private JButton choose;
    private JButton custom;
//    private Draw draw_;

    public Palette(Model model_){
        model = model_;

        JPanel p = new JPanel();
        pTop = new JPanel();
        pBottom = new JPanel();

        // Set the layout strategy to a grid with 2 rows and 3 columns.
        p.setLayout(new BorderLayout());
        pTop.setLayout(new GridLayout(9, 2)); //color palette
        pBottom.setLayout(new GridLayout(5,1)); //stroke palette

        colorPalette();
        StrokePalette();

        //nested layout
        p.add(pTop,BorderLayout.NORTH);
        p.add(pBottom,BorderLayout.SOUTH);
        this.add(p); //put back layout to frame

        this.setBackground(Color.LIGHT_GRAY);
        System.out.println("Palette");
    }

//    public Palette(Model model_,JPanel v){
//        model = model_;
//        vi = v;
//
//        draw_ = new Draw(vi,Color.BLACK,new BasicStroke(1),model);
//
//        JPanel p = new JPanel();
//        pTop = new JPanel();
//        pBottom = new JPanel();
//
//        // Set the layout strategy to a grid with 2 rows and 3 columns.
//        p.setLayout(new BorderLayout());
//        pTop.setLayout(new GridLayout(8, 2)); //color palette
//        pBottom.setLayout(new GridLayout(5,1)); //stroke palette
//
//        colorPalette();
//        StrokePalette();
//
//        //nested layout
//        model.addColorPalette(p,pTop);
//        model.addStrokePalette(p,pBottom);
////        p.add(pTop,BorderLayout.NORTH);
////        p.add(pBottom,BorderLayout.SOUTH);
//        this.add(p); //put back layout to frame
//
//        this.setBackground(Color.LIGHT_GRAY);
//        System.out.println("Palette");
//    }


    void colorPalette(){
        JButton one = new JButton();
        JButton two = new JButton();
        JButton three = new JButton();
        JButton four = new JButton();
        JButton five = new JButton();
        JButton six = new JButton();
        JButton seven = new JButton();
        JButton eight = new JButton();
        JButton nine = new JButton();
        JButton ten = new JButton();
        JButton eleven = new JButton();
        JButton twelve = new JButton();
        choose = new JButton("Pick Color");
        custom = new JButton("");

        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.BLACK);
            }
        });
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.WHITE);
            }
        });
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.LIGHT_GRAY);
            }
        });
        four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.DARK_GRAY);
            }
        });
        five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.BLUE);
            }
        });
        six.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.CYAN);
            }
        });
        seven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.GREEN);
            }
        });
        eight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.YELLOW);
            }
        });
        nine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.ORANGE);
            }
        });
        ten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.MAGENTA);
            }
        });
        eleven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.PINK);
            }
        });
        twelve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeColor(Color.RED);
            }
        });
        choose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.colorChooser();
            }
        });
        custom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.colorCustom = true;
                model.colorChooser();
            }
        });


        one.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        two.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        three.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        four.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        five.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        six.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        seven.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        eight.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        nine.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        ten.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        eleven.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        twelve.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        choose.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        custom.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));


        //Set the color for each color palette
        one.setBackground(Color.BLACK);
        two.setBackground(Color.WHITE);
        three.setBackground(Color.LIGHT_GRAY);
        four.setBackground(Color.DARK_GRAY);
        five.setBackground(Color.BLUE);
        six.setBackground(Color.CYAN);
        seven.setBackground(Color.GREEN);
        eight.setBackground(Color.YELLOW);
        nine.setBackground(Color.ORANGE);
        ten.setBackground(Color.MAGENTA);
        eleven.setBackground(Color.PINK);
        twelve.setBackground(Color.RED);
        choose.setBackground(Color.LIGHT_GRAY);
        custom.setBackground(Color.LIGHT_GRAY);

//        one.setForeground(Color.BLACK);
//        two.setForeground(Color.WHITE);
//        three.setForeground(Color.DARK_GRAY);
//        four.setForeground(Color.LIGHT_GRAY);
//        five.setForeground(Color.BLUE);
//        six.setForeground(Color.CYAN);
//        seven.setForeground(Color.GREEN);
//        eight.setForeground(Color.YELLOW);
//        nine.setForeground(Color.ORANGE);
//        ten.setForeground(Color.MAGENTA);
//        eleven.setForeground(Color.PINK);
//        twelve.setForeground(Color.RED);
        choose.setForeground(Color.BLACK);

        //make the background shown
        one.setOpaque(true);
        two.setOpaque(true);
        three.setOpaque(true);
        four.setOpaque(true);
        five.setOpaque(true);
        six.setOpaque(true);
        seven.setOpaque(true);
        eight.setOpaque(true);
        nine.setOpaque(true);
        ten.setOpaque(true);
        eleven.setOpaque(true);
        twelve.setOpaque(true);
        choose.setOpaque(true);
        custom.setOpaque(true);


        // Add the components.
        pTop.add(one);
        pTop.add(two);
        pTop.add(three);
        pTop.add(four);
        pTop.add(five);
        pTop.add(six);
        pTop.add(seven);
        pTop.add(eight);
        pTop.add(nine);
        pTop.add(ten);
        pTop.add(eleven);
        pTop.add(twelve);
        pTop.add(choose);
        pTop.add(custom);

    }

    void StrokePalette(){

        JButton a = new JButton();
        JButton b = new JButton();
        JButton c = new JButton();
        JButton d = new JButton();

        a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.changeStroke(new BasicStroke(1));
            }
        });

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.changeStroke(new BasicStroke(3));
            }
        });
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.changeStroke(new BasicStroke(5));
            }
        });
        d.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.changeStroke(new BasicStroke(8));
            }
        });

        a.setIcon(new ImageIcon("line1.GIF"));
        b.setIcon(new ImageIcon("line2.GIF"));
        c.setIcon(new ImageIcon("line3.GIF"));
        d.setIcon(new ImageIcon("line4.GIF"));

        a.setBackground(Color.WHITE);
        b.setBackground(Color.WHITE);
        c.setBackground(Color.WHITE);
        d.setBackground(Color.WHITE);

        a.setFocusable(false);
        b.setFocusable(false);
        c.setFocusable(false);
        d.setFocusable(false);

        a.setOpaque(true);
        b.setOpaque(true);
        c.setOpaque(true);
        d.setOpaque(true);


        pBottom.add(a);
        pBottom.add(b);
        pBottom.add(c);
        pBottom.add(d);


        //        pBottom.add(new JComponent() {
//            public void paintComponent(Graphics g){
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setStroke(new BasicStroke(10));
//                g2.setColor(Color.BLACK);
//                g2.draw(new Line2D.Float(30,20,80,20));
//            }
//        });


        //GridBagConstraints gc = new GridBagConstraints();
//
//        a.setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));
//        b.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
//        c.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
//        d.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
//
//        a.setBackground(Color.LIGHT_GRAY);
//        b.setBackground(Color.BLACK);
//        c.setBackground(Color.BLACK);
//        d.setBackground(Color.BLACK);
//
//        a.setForeground(Color.LIGHT_GRAY);
//        b.setForeground(Color.BLACK);
//        c.setForeground(Color.BLACK);
//        d.setForeground(Color.BLACK);
//
//        a.setSize(40,10);
//
//        a.setOpaque(true);
//        b.setOpaque(true);
//        c.setOpaque(true);
//        d.setOpaque(true);
//
//        gc.gridwidth=1;
//        gc.gridheight = 1;
//        gc.gridx = 2;
//        gc.gridy = 1;
//        gc.weightx = 0.01;
//        gc.weighty = 0.2;
//        gc.anchor = GridBagConstraints.NORTH;
//        pBottom.add(a,gc);
//        gc.gridy++;
//
//        pBottom.add(b,gc);
//        gc.gridy++;
//
//        gc.anchor = GridBagConstraints.SOUTH;
//        pBottom.add(c,gc);
//        gc.gridy++;
//
//        pBottom.add(d,gc);

//        // Add the components.
//        pBottom.add(a);
//        pBottom.add(b);
//        pBottom.add(c);
//        pBottom.add(d);

    }


    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        System.out.println("update palette");

        if(model.chooseCol){ //use color picker
            if(model.colorCustom){
                //custom color button for bonus marks
                System.out.println("color chosen custom color: "+model.curColor);
                custom.setBackground(model.curColor);
                custom.setOpaque(true);
                pTop.add(custom);
                model.chooseColDone();
            }else{
                //pick color button
                System.out.println("color chosen pick color: "+model.curColor);
                choose.setBackground(model.curColor);
                choose.setOpaque(true);
                pTop.add(choose);
                model.chooseColDone();
            }

        }
    }
}
