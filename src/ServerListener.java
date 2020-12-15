import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-14
 * Project: Preschool_own_project
 * Copyright: MIT
 */

// sends and recieves objects

public class ServerListener extends Thread{

    Socket clientSocket;

    public ServerListener(Socket clientSocket) throws Exception{
        this.clientSocket = clientSocket;
        //t.start();
    }


    public void run() {

        try(ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
            ){

            DataTransferObject input;

            ServerProtocoll p = new ServerProtocoll();
            input = p.startConnection();

            outObj.writeObject(p.processInput(null));

            while((input = (DataTransferObject) inObj.readObject()) != null){
                outObj.writeObject(((p.processInput(input))));

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
