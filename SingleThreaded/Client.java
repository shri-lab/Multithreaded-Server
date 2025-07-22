import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Client{
public void run() throws IOException{
    int port = 8010;
    InetAddress address = InetAddress.getByName("localhost");
    Socket socket = new Socket(address,port);
    PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    toSocket.println("Hello from the client");
    String line = fromSocket.readLine();
    System.out.println("Response from the server: "+line);
    toSocket.close();
    fromSocket.close();
    socket.close();
}
public static void main(String[] args) throws IOException{
    try{
        Client client = new Client();
        client.run();
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
