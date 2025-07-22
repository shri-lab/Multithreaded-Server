import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server
{
    public void run() throws IOException{
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true){
            try{
               System.out.println("Server is listeneing to the port"+port);
               Socket acceptedConnection = socket.accept();
               System.out.println("Connection accepted from client "+acceptedConnection.getRemoteSocketAddress());
               PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
               BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
               toClient.println("Hello from the server");
               toClient.close();
               fromClient.close();
               acceptedConnection.close();
               
               break;
               
            }catch(IOException e){
                e.printStackTrace();
        }

    }
}
public static void main(String[] args) throws IOException{
    Server server = new Server();
   try{
    server.run();
   }catch(Exception e){
    e.printStackTrace();
}

}
}
