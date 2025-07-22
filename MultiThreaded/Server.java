
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.io.FileReader;

public class Server {
    public Consumer<Socket> getConsumer(){
        return (clientSocket) -> {
            try (
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                String filename = fromClient.readLine(); // Read filename from client
                String fileContent;
                try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
                    fileContent = fileReader.readLine(); // Read first line (or loop for all lines)
                } catch (IOException e) {
                    fileContent = "Error: File not found or could not be read.";
                }
                toClient.println(fileContent); // Send file content or error to client
            } catch(IOException e){
                e.printStackTrace();
            }
        };
    }
    
    public static void main(String[] args){
        int port = 8010;
        Server server = new Server();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listeneing to the port"+port);
            while(true){
                Socket acceptedSocket =  serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }  
    
    }

