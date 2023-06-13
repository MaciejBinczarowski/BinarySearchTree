import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class WebHandler<T extends Comparable<T>>
{
    private BinaryTree<T> binaryTree = new BinaryTree<T>();
    private Parseable<T> parser;
    private BufferedReader clientInput;
    private PrintWriter output;

    public WebHandler(Parseable<T> parser, BufferedReader clientInput, PrintWriter output)
    {
        this.parser = parser;
        this.clientInput = clientInput;
        this.output = output;
    }

    public void run()
    {
        String inputString;
        
        try
        {
            do
            {
                output.println("Choose action");
                output.println("waiting for response");

                inputString = clientInput.readLine();

                if (inputString.toUpperCase().equals("INSERT"))
                {
                    output.println("Enter value:");
                    output.println("waiting for response");

                    try
                    {
                        inputString = clientInput.readLine();
                    
                        T preparedInput = parser.parse(inputString);
                        binaryTree.insert(preparedInput);

                        output.println("Value added correctly, current values: ");
                        drawTree();
                    }
                    catch (Exception exception)
                    {
                        output.println("Wrong value type!\n");
                    }
                }
                else if (inputString.toUpperCase().equals("SEARCH"))
                {
                    output.println("Enter wanted value:");
                    output.println("waiting for response");

                    try
                    {
                        inputString = clientInput.readLine();
                    
                        T preparedInput = parser.parse(inputString);
                        
                        if (binaryTree.search(preparedInput))
                        {
                            output.println("Value found");
                        }
                        else
                        {
                            output.println("Value doesn't exist");
                        }
                    }
                    catch (Exception exception)
                    {
                        output.println("Wrong value type!\n");
                    }
                }
                else if (inputString.toUpperCase().equals("DELETE"))
                {
                    output.println("Enter wanted value:");
                    output.println("waiting for response");

                    try
                    {
                        inputString = clientInput.readLine();
                    
                        T preparedInput = parser.parse(inputString);
                        
                        if (binaryTree.delete(preparedInput))
                        {
                            output.println(inputString + " removed correctly");
                        }
                        else
                        {
                            output.println(inputString + " doesn't exist");
                        }

                        output.println("Current values: ");
                        drawTree();
                    }
                    catch (Exception exception)
                    {
                        output.println("Wrong value type!\n");
                    }
                }
                else if (inputString.toUpperCase().equals("DRAW"))
                {
                    drawTree();
                }
                else if (inputString.toUpperCase().equals("EXIT"))
                {
                    return;
                }
                else
                {
                    output.println("Wrong action");
                }
            } 
            while (!inputString.equals("exit"));
        }
        catch (Exception exception)
        {

        }

    }

    private void drawTree()
    {
        PrintStream previousConsole = System.out;
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));

        System.out.println("\n==================\n");

        binaryTree.draw();

        output.print(newConsole.toString());

        output.println("\n==================");

        System.setOut(previousConsole);
    }
}
