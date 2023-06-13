import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Client 
{
    private Socket socket;
    private PrintWriter output;
    private BufferedReader serverInput;
    private MainSceneController mainSceneController;
    private Thread reciver;

    public Client()
    {
        try
        {
            Socket socket = new Socket("localHost", 1234);
            this.socket = socket;

            OutputStream outputStream = socket.getOutputStream();
            this.output = new PrintWriter(outputStream, true);

            InputStream serverInputStream = socket.getInputStream();
            this.serverInput = new BufferedReader(new InputStreamReader(serverInputStream));
            // socket.close();
        }
        catch (Exception exception)
        {
            closeEverything();
            // System.out.println(exception.getMessage());
            // System.out.println(exception.getStackTrace());
        }
    }

    public void sendMessageToServer(String message)
    {
        output.println(message);
    }

    public void reciveMessageFromServer()
    {
        this.reciver = new Thread(new Runnable() {
            
            @Override
            public void run()
            {
                // ArrayList<String> messages = new ArrayList<String>();
                String messages = "";
                while(!socket.isClosed())
                {
                    try 
                    {
                        String messageFromServer = serverInput.readLine();
                        if (!messageFromServer.equals("waiting for response"))
                        {
                            messages += "\n" + messageFromServer;
                        }
                        else
                        {
                            mainSceneController.displayMessage(messages);
                            messages = "";
                        }
                    } 
                    catch (IOException e) 
                    {
                        // TODO Auto-generated catch block
                        closeEverything();
                        // e.printStackTrace();
                    }
                }
            }
        });

        reciver.setDaemon(true);
        reciver.start();
    }

    public void setMainSceneController(MainSceneController mainSceneController)
    {
        this.mainSceneController = mainSceneController;
        reciveMessageFromServer();
    }

    public void closeEverything() 
    {
        try {
            output.close();
            serverInput.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("błąd");
            e.printStackTrace();
        }
    }
}
