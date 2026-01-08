import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    private JLabel statusLbl;
    private JLabel serverLbl;

    public MyPanel(){

        createGui();

        sendBtn.addActionListener(this);

        disconnectBtn.addActionListener(this);


    }



    public void createGui(){


        setLayout(new BorderLayout());

        businessLbl = new JLabel("Business Name: ");
        businessTxt = new JTextField(10);
        businessNumLbl = new JLabel("Business Number: ");
        businessNumTxt = new JTextField(5);
        itemsPurchaseLbl = new JLabel("Amount: ");
        itemsPurchaseTxt = new JTextField(5);
        sendBtn = new JButton("Send");
        disconnectBtn = new JButton("Disconnect");
        statusLbl = new JLabel();
        serverLbl = new JLabel("Server Response: ");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

        topPanel.add(businessLbl);
        topPanel.add(businessTxt);
        topPanel.add(businessNumLbl);
        topPanel.add(businessNumTxt);
        topPanel.add(items);
        topPanel.add(itemsPurchaseLbl);
        topPanel.add(itemsPurchaseTxt);
        topPanel.add(sendBtn);
        topPanel.add(disconnectBtn);

        bottomPanel.add(serverLbl);
        bottomPanel.add(statusLbl);

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);


        statusLbl.setVisible(false);
        statusLbl.setOpaque(true);
        disconnectBtn.setVisible(false);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

//Client class of our shared resource

        try(Socket socket = new Socket("127.0.0.1",9999)){

           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           String outputLine;

           out.println(businessTxt.getText() + ", " + businessNumTxt.getText() + ", " + (String)items.getSelectedItem() + ", " + itemsPurchaseTxt.getText());


           if ((outputLine = in.readLine()) != null){
               System.out.println(outputLine);


               if (outputLine.contains("100")){
                   statusLbl.setBackground(Color.GREEN);
                   disconnectBtn.setVisible(true);
               }
               else {
                   statusLbl.setBackground(Color.RED);
               }
           }

           statusLbl.setVisible(true);
           statusLbl.setText(outputLine);


           if (e.getSource() == disconnectBtn){
               JOptionPane.showMessageDialog(this,"GoodBye!","Leaving Server: ", JOptionPane.INFORMATION_MESSAGE);
               SwingUtilities.getWindowAncestor(this).dispose();
               in.close();
               out.close();
               socket.close();
           }




        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }



}
