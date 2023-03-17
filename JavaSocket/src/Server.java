import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8000);
        
        Socket serverSocket = server.accept();

        DataInputStream requestFromClient = new DataInputStream(serverSocket.getInputStream());
        DataOutputStream responseToClient = new DataOutputStream(serverSocket.getOutputStream());
        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(System.in));

        String receivedFromClient = "", responseContent = "";
        while(!receivedFromClient.equals("EXIT")){
            receivedFromClient = requestFromClient.readUTF();
            System.out.println("CLIENT:" + receivedFromClient);
            responseContent = responseBuffer.readLine();
            responseToClient.writeUTF(responseContent);
            responseToClient.flush();
        }
        requestFromClient.close();
        server.close();
    }
}
