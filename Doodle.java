public class Doodle{
    //constructor
    public Doodle(){
        Model model = new Model();
        View view = new View(model);
//        Canvas canv = new Canvas(model);
//        Playback pb = new Playback(model);
//        Palette pa = new Palette(model);

        //update the view
        model.addObserver(view.c);
        model.addObserver(view.pal);
        model.addObserver(view.pc);
        model.notifyObservers();

    }

    public static void main(String[] args){
//        SwingUtilities.invokeLater(new Runnable(){
//            public void run(){
                Doodle pd = new Doodle();
//            }
//        });
    }
}
