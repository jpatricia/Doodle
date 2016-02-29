import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.ArrayList;

class View extends JPanel{
    private Model model;
    private JFrame f;
    public Canvas c;
    public Palette pal;
    public Playback pc;
    private JPanel p;
    private JScrollPane scrollpane;

    public View(Model model_){
        model = model_;

        JMenuBar jmb;
        p = new JPanel(new BorderLayout());
        c = new Canvas(model);
        pal = new Palette(model);
        pc = new Playback(model);

        scrollpane = new JScrollPane(c);
        scrollpane.setPreferredSize(new Dimension(500,500));
        c.setPreferredSize(new Dimension(800,600));

        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ////////// MENU BAR //////////
        f = new JFrame("Doodle"); //create a window
        f.getContentPane().setLayout(new BorderLayout());
        f.setSize(1000,700);

        //Set menu bar
        jmb = MenuBar();
        f.setJMenuBar(jmb);

        p.setOpaque(true);
        f.getContentPane().add(p);

        //add canvas
        p.add(scrollpane,BorderLayout.CENTER);

        //add palette
        p.add(pal,BorderLayout.WEST);

        //add playback control
        p.add(pc,BorderLayout.SOUTH);

//        //add canvas, palette, and playback
//        model.addCanvas(p);
//        model.addPalette(p);
//        model.addPlayback(p);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private JMenuBar MenuBar(){
        JMenuBar jmb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu viewmenu = new JMenu("View");
        JMenuItem New = new JMenuItem("Create New");
        JMenuItem Load = new JMenuItem("Load");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Exit = new JMenuItem("Exit");
        JMenuItem Fit  = new JMenuItem("Fit");
        JMenuItem Full = new JMenuItem("Full");

        //add menu items to menu
        file.add(New);
        file.add(Load);
        file.add(Save);
        file.add(Exit);
        viewmenu.add(Fit);
        viewmenu.add(Full);

        //add file and view to menu bar
        jmb.add(file);
        jmb.add(viewmenu);

        New.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createNew();
            }
        });

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = System.getProperty("user.home")+"/Desktop".replace("\\","/");
                JFileChooser fileChooser = new JFileChooser(path);
                int returnVal = fileChooser.showSaveDialog(f);

                //If user chooses to save the file
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        FileOutputStream fs = new FileOutputStream(file);
                        ObjectOutputStream os = new ObjectOutputStream(fs);

                        //write infomration to a txt file
                        os.writeObject(model.getPointsList());
                        os.writeObject(model.getCheckStart());
                        os.writeObject(model.getColor());
                        os.writeObject(model.getStroke());
                        os.writeObject(model.getCompletePoints());
                        os.writeObject(model.getEndLineIndex());

                        os.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = System.getProperty("user.home") + "/Desktop".replace("\\", "/");
                JFileChooser fileChooser = new JFileChooser(path);
                int returnVal = fileChooser.showOpenDialog(f);

                //If user chooses to open a file
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);

                        //read infomration to a txt file
                        ArrayList<Point> readPoint = (ArrayList<Point>) ois.readObject();
                        ArrayList<Point> readCheckStart = (ArrayList<Point>) ois.readObject();
                        ArrayList<Color> readColor = (ArrayList<Color>) ois.readObject();
                        ArrayList<Float> readStroke = (ArrayList<Float>) ois.readObject();
                        ArrayList<Point> readComplete = (ArrayList<Point>) ois.readObject();
                        ArrayList<Integer> readEndLine = (ArrayList<Integer>) ois.readObject();

                        model.setModel(readPoint, readCheckStart, readColor, readStroke, readComplete, readEndLine);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Fit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // model.SetFit();
                model.fit(f.getWidth(),f.getHeight());
            }
        });

        Full.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.full();
            }
        });

        f.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("window width: "+ f.getWidth());
                System.out.println("Window Height: "+f.getHeight());
                if(model.fitMenu){
                    System.out.println("fit resize");
                    //currently on fit size mode
                    model.fit(f.getWidth(),f.getHeight());
                    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                }else{
//                    if(f.getWidth() <= 450){
//                        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//                    }
//                    if(f.getHeight() <= 450){
//                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//                    }
                    model.full();
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {}

            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        return jmb;
    }


//    @Override //update the view by repainting the canvas
//    public void update(Observable arg0, Object arg1){
//        System.out.println("update view");
//        repaint();
//    }

}
