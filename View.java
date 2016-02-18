import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class View extends JPanel implements Observer {
    private Model model;
    private JFrame f;

    public View(Model model_){
        model = model_;

        JMenuBar jmb;
        JPanel p = new JPanel(new BorderLayout());
        Canvas c = new Canvas(model);
        Palette pal = new Palette(model);
        Playback pc = new Playback(model);

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


    @Override //update the view by repainting the canvas
    public void update(Observable arg0, Object arg1){
        repaint();
    }

}
