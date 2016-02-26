import javax.swing.*;
import java.awt.*;

class View extends JPanel{
    private Model model;
    private JFrame f;
    public Canvas c;
    public Palette pal;
    public Playback pc;

    public View(Model model_){
        model = model_;

        JMenuBar jmb;
        JPanel p = new JPanel(new BorderLayout());
        c = new Canvas(model);
        pal = new Palette(model);
        pc = new Playback(model);


        ////////// MENU BAR //////////
        f = new JFrame("Doodle"); //create a window
        f.getContentPane().setLayout(new BorderLayout());
        f.setSize(800,600);

        //Set menu bar
        jmb = MenuBar();
        f.setJMenuBar(jmb);

        p.setOpaque(true);
        f.getContentPane().add(p);

        //add canvas
        p.add(c,BorderLayout.CENTER);

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

        return jmb;
    }


//    @Override //update the view by repainting the canvas
//    public void update(Observable arg0, Object arg1){
//        System.out.println("update view");
//        repaint();
//    }

}
