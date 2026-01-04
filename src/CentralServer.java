import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CentralServer {

    public static void main(String[] args) {

        System.out.println("Server is starting.");

        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(9999);

            while (true){

                Socket clientSocket = null;

                try{
                    clientSocket = serverSocket.accept();

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader( new InputStreamReader( (clientSocket.getInputStream()) ) );
                    String outputLine;



                } catch (IOException e) {

                    System.err.println("Accept failed");

                }


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
