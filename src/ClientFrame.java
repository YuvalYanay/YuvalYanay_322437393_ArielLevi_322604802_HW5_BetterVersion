import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {


    public ClientFrame(){

        super("ClientGUI");

        setSize(600,400);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        MyPanel mP = new MyPanel();

        add(mP);
        setVisible(true);




    }



}
