import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Client 
{
    public static void main(String[] args) 
    {
        try
        {
            Socket socket = new Socket("localHost", 1234);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter output = new PrintWriter(outputStream, true);

            InputStream serverInputStream = socket.getInputStream();
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(serverInputStream)); 

            Console console = System.console();
            String text;
            String recivedLine;

            do
            {
                text = "";
                recivedLine = serverInput.readLine();

                if (recivedLine.equals("waiting for response"))
                {
                    text = console.readLine();
                    output.println(text);
                }
                else
                {
                    System.out.println(recivedLine);
                }
                

            } while (!text.toUpperCase().equals("EXIT"));

            socket.close();
        }
        catch (Exception exception)
        {
            System.out.println("nie jestem w serverze :(");
            System.out.println(exception.getStackTrace());
        }
    }
}
