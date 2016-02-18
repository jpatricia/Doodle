import javax.swing.*;

/**
 * Created by Janice on 2/14/2016.
 */
public class Doodle{
    //constructor
    public Doodle(){
        Model model = new Model();
        View view = new View(model);

        //update the view
        model.addObserver(view);
        model.notifyObservers();

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Doodle pd = new Doodle();
            }
        });
    }
}
