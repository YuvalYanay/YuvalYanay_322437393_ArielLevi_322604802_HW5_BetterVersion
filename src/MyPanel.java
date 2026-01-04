import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener  {

    private JTextField businessTxt;
    private JLabel businessLbl;
    private JTextField businessNumTxt;
    private JLabel businessNumLbl;
    private final String[] arrClothes = {"Sun-Glasses","Belt","Scarf"};
    private final JComboBox items = new JComboBox<>(arrClothes);
    private JTextField itemsPurchaseTxt;
    private JLabel itemsPurchaseLbl;
    private JButton sendBtn;
    private JButton disconnectBtn;

    public MyPanel(){

        createGui();

        sendBtn.addActionListener(e ->{



//            try(Socket socket = new Socket("127.0.0.1",9999)){
//
//
//
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }

        });
    }



    public void createGui(){
        businessLbl = new JLabel("Business Name: ");
        businessTxt = new JTextField(10);
        businessNumLbl = new JLabel("Business Number: ");
        businessNumTxt = new JTextField(5);
        itemsPurchaseLbl = new JLabel("Amount: ");
        itemsPurchaseTxt = new JTextField(5);
        sendBtn = new JButton("Send");
        disconnectBtn = new JButton("Disconnect");


        add(businessLbl);
        add(businessTxt);
        add(businessNumLbl);
        add(businessNumTxt);
        add(items);
        add(itemsPurchaseLbl);
        add(itemsPurchaseTxt);
        add(sendBtn);
        add(disconnectBtn);


        setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }



}
