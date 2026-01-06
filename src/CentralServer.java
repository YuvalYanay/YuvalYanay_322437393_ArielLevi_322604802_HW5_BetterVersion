import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class CentralServer implements Runnable{

    public static void main(String[] args) {

        System.out.println("Server is starting on port 9999.");

       try(ServerSocket serverSocket = new ServerSocket(9999)){

           Socket socket = null;

           while (true){

               socket = serverSocket.accept();
               System.out.println("Client " + socket + " is connecting to the server.");

           }

           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


           String inputLine;

           while(){

           }

           new Thread(new ClientHandler())

       } catch (IOException e){

           System.err.println("Error on port 4444: " + e.getMessage());

       }

    }
}
