import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    public static void main(String[] args) 
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(1234);

            while (true)
            {
                Socket socket = serverSocket.accept();

                System.out.println("New client connected");

                new ClientThread(socket).start();

                //nowy fred
            }
        }
        catch (Exception exception)
        {
            System.out.println("elo jestem w serverze");
            System.out.println(exception.getStackTrace());
        }
    }
}
