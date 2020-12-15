import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-12-14
 * Project: Preschool_own_project
 * Copyright: MIT
 */
public class Server {

    //ServerSocket serverSocket;


    public Server(){
        try(ServerSocket serverSocket = new ServerSocket(12345)){

            while (!serverSocket.isClosed()){
                try{
                    final Socket socketToClient = serverSocket.accept();
                    ServerListener serverListener = new ServerListener(socketToClient);
                    serverListener.start();
                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
