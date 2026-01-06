import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CentralServer{

    public static void main(String[] args) {

        List<Client> clients = new ArrayList<>();
        System.out.println("Server is starting on port 9999.");

       try(ServerSocket serverSocket = new ServerSocket(9999)){

           Socket socket = null;
           while (true){

               socket = serverSocket.accept();
               System.out.println("Client " + socket.getLocalPort() + " is connecting to the server.");
               ClientHandler clientHandler = new ClientHandler(socket,clients);
               new Thread(clientHandler).start();

           }

       } catch (IOException e){

           System.err.println("Error on port 4444: " + e.getMessage());

       }

    }
}
