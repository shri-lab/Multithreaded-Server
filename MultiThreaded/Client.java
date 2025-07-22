
import java.net.InetAddress;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Client {   
    public Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run(){
                int port = 8010;
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket("localhost",port);
                    try (
                        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                    ) {
                        toSocket.println("tt.txt"); // Send filename to server
                        String fileContent = fromSocket.readLine(); // Read file content or error
                        System.out.println("File content: " + fileContent);
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };
    }
    
    public static void main(String[] args){
    Client client = new Client();
    for(int i = 0; i < 10; i++){
        try{
            Thread thread = new Thread(client.getRunnable());
            thread.start();
        }catch(Exception e){
           return;
        }
    }
}
}
    

