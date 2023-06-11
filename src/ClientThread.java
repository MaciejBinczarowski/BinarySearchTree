import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientThread extends Thread
{
    private Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            InputStream inputStream = socket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter output = new PrintWriter(outputStream, true);

            HashMap<String, WebHandler<?>> webHandlersMap = new HashMap<String, WebHandler<?>>()
            {{
                put("Integer", new WebHandler<Integer>(new ParserInt(), input, output));
                put("Double", new WebHandler<Double>(new ParserDouble(), input, output));
                put("String", new WebHandler<String>(new ParserString(), input, output));
            }};

            String line;
            WebHandler<?> webHandler;
            
            do
            {
                output.println("Wprowadź typ drzewa");
                output.println("waiting for response");
                line = input.readLine();
                webHandler = webHandlersMap.get(line);
            } while (webHandler == null);
            
            webHandler.run();

            System.out.println("Client disconeccted");

            socket.close();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getStackTrace());
        }
    }
}
