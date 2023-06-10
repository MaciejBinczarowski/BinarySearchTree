import java.util.Scanner;

public class ConsoleHandler<T extends Comparable<T>>
{
    private BinaryTree<T> bst = new BinaryTree<T>();
    private Parseable<T> parser;

    public ConsoleHandler(Parseable<T> parser)
    {
        this.parser = parser;
    }
    

    public void run()
    {
        System.out.println("Drzewo typu: ");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String option = scanner.nextLine();

            if (option.equals("INSERT"))
            {   
                try
                {
                    System.out.println("Wprowadź wartość: ");
                    T input = parser.parse(scanner.nextLine());
                    bst.insert(input);
                    System.out.println("Pomyślnie dodano wartość");
                }
                catch (NumberFormatException exception)
                {
                    System.out.println(exception.getMessage());
                    System.out.println("Nie dodano wartości");
                }
            }

            else if (option.equals("SEARCH"))
            {   
                try
                {
                    System.out.println("Wprowadź wartość: ");
                    T input = parser.parse(scanner.nextLine());
                    System.out.println(bst.search(input));
                    System.out.println("Pomyślnie wyszukano wartość");
                }
                catch (NumberFormatException exception)
                {
                    System.out.println(exception.getMessage());
                    System.out.println("Wartość niewłasćiwego typu");
                }
            }

            else if (option.equals("DRAW"))
            {
                System.out.println("Rysuję drzewo");
                bst.draw();
                System.out.println("Pomyśline narysowano drzewo");
            }

            else if (option.equals("DELETE"))
            {
                try
                {
                    System.out.println("Wprowadź wartość: ");
                    T input = parser.parse(scanner.nextLine());
                    if (bst.delete(input))
                    {
                        System.out.println("Usunięto element");
                    }
                    else
                    {
                        System.out.println("Taki element nie istnieje, nic nie usunięto");
                    }
                }
                catch (NumberFormatException exception)
                {
                    System.out.println(exception.getMessage());
                    System.out.println("Niewłaściwy typ wartości");
                }
            }

            else if (option.equals("EXIT"))
            {
                break;
            }

            else
            {
                System.out.println("Nie ma takiej operacji");
            }

        }
        scanner.close();
    }
}
