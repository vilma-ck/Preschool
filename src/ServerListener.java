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

public class ServerListener implements Runnable{

    Socket clientSocket;
    Thread t;

    public ServerListener(Socket clientSocket) throws Exception{
        this.clientSocket = clientSocket;
        t.start();
    }


    @Override
    public void run() {

        try(ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
            ){

            String input;

            ServerProtocoll p = new ServerProtocoll();

            outObj.writeObject(p.processInput(null));

            while((input = (String) inObj.readObject()) != null){
                outObj.writeObject(((p.processInput(input))));

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
