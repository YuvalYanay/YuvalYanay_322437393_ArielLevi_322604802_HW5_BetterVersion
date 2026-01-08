import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{

    private Socket socket;
    private List<Client> clients;
    private static final Object LOCK = new Object();

    public ClientHandler(Socket socket,List<Client> clients){

        this.socket = socket;
        this.clients = clients;

    }

    @Override
    public void run(){


        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputline;
            String[] clientData;
            String status;

            while ((inputline = in.readLine()) != null){

                System.out.println("Data recieved from client " + socket.getPort() + ": " + inputline);
                status = dataStatus(getData(inputline));

                System.out.println(status);

                if(status.contains("100")){
                    out.println(status);
                }
                else {
                    out.println(status);
                }


            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client handler exception: " + e.getMessage());
        }


    }


    public String[] getData(String message){

        String[] data;
        String regex = ",";
        data = message.split(regex);

        return data;
    }

    public String dataStatus(String[] data){


        String businessName = data[0].trim();
        String businessNum = data[1].trim();
        String cloth = data[2].trim();
        String amount = data[3].trim();


        try{

            if(businessName.isEmpty() || businessNum.isEmpty() || cloth.isEmpty() || amount.isEmpty()){
                System.out.println("Failure: Missing values");
                return "Failure 200: Missing values";
            }

            int amountInt = Integer.parseInt(amount);

            for (Client client : clients){

                if (amountInt > 50) {

                    System.out.println("Failure: You can't order more than 50 units of " + cloth);
                    return "Failure 202: You can't order more than 50 units of " + cloth;

                }

                if (businessName.equals(client.getBusinessName()) && !businessNum.equals(client.getBusinessNum())){

                    synchronized (LOCK){
                        client.setPurchaseAmount(Integer.parseInt(amount) + client.getPurchaseAmount());
                    }

                    System.out.println("Success: Update details");
                    return "Success 100: Update completed";

                } else if (businessName.equals(client.getBusinessName()) && !businessNum.equals(client.getBusinessNum())) {
                    System.out.println("Failure: Unmatched data between business name and business number");
                    return "Failure 201: Unmatched data between business name and business number";
                }


            }


        } catch (IllegalArgumentException e){

            System.out.println(e.getMessage());
        }

        synchronized (LOCK){
            Client newClient = new Client(businessName,Integer.parseInt(businessNum),cloth,Integer.parseInt(amount));
            clients.add(newClient);
            System.out.println("Success: Client added");
            return "Success 100: Client added";
        }


    }

}
