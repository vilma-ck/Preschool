import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-14
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class Client {
    // utifrån MultiUSerTelefonbok

    public Client() throws UnknownHostException{
        InetAddress iAdr = InetAddress.getLocalHost();
        int port = 12345;

        try (Socket clientSocket = new Socket(iAdr, port);
             ObjectOutputStream outObject = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream inObject = new ObjectInputStream(clientSocket.getInputStream());
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));){

            Object fromServer;
            String fromUser;

            while((fromServer = inObject.readObject()) != null){
                if(fromServer instanceof String){
                    System.out.println(fromServer);
                } // else if sats om man skickar en viss typ av objekt
            }

            fromUser = userIn.readLine();
            if(fromUser != null){
                outObject.writeObject(fromUser);
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws UnknownHostException {
        new Client();
    }
}
