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
                clientData = getData(inputline);
                status = dataStatus(clientData);

                System.out.println(status);
                out.println("Server: " + status);
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

        String businessName = data[0];
        Integer businessNum = Integer.parseInt(data[1].trim());
        String cloth = data[2];
        Integer amount = Integer.parseInt(data[3].trim());


        try{
            for (Client client : clients){

                if (businessName.equals(client.getBusinessName()) && businessNum.equals(client.getBusinessNum())){

                    synchronized (LOCK){
                        client.setPurchaseAmount(amount + client.getPurchaseAmount());
                    }

                    return "Update Succeeded : 100";

                } else if (businessName.isEmpty() || businessNum == null || cloth.isEmpty() || amount == null) {

                    return "Missing Data : 200";

                } else if (businessName.equals(client.getBusinessName()) && !businessNum.equals(client.getBusinessNum())) {

                    return "Error - Unmatched data between business name and business number : 201";

                } else if (amount > 50) {

                    return "Error - You can't order more than 50 units of " + cloth;

                }
                else {
                    Client newClient = new Client(businessName,businessNum,cloth,amount);
                    clients.add(newClient);
                    return "Success : 100";
                }

            }


            Client newClient = new Client(businessName,businessNum,cloth,amount);
            clients.add(newClient);
            return "Success : 100";

        } catch (IllegalArgumentException e){

            System.out.println(e.getMessage());
        }

        return "Error - Illegal data : 203";

    }

}
