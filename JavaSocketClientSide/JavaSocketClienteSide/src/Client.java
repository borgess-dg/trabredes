import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = new Socket("localhost", 8000);
        DataOutputStream responseToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream requestToServer = new DataInputStream(clientSocket.getInputStream());
        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(System.in));

        String receivedFromServer = "", responseContent = "";
        while(!receivedFromServer.equals("EXIT")){
            responseContent = responseBuffer.readLine();
            responseToServer.writeUTF(responseContent);
            responseToServer.flush();
            receivedFromServer = requestToServer.readUTF();
            System.out.println(receivedFromServer);
        }
        responseToServer.close();
        clientSocket.close();
    }
}