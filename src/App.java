import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception 
    {
        // BinaryTree<Integer> drzewo = new BinaryTree<Integer>();
        // drzewo.insert(5);
        // drzewo.insert(10);
        // drzewo.insert(0);
        // // drzewo.insert(9);
        // // drzewo.insert(100);
        // // drzewo.insert(-2);
        // // drzewo.insert(0);
        // // drzewo.insert(101);

        // System.out.println(drzewo.search(8));
        // System.out.println(drzewo.search(9));
        // System.out.println(drzewo.search(7));

        // // drzewo.delete(5);
        // System.out.println(drzewo.search(5));

        // drzewo.draw();

        // BinaryTree<String> drzewoStringow = new BinaryTree<String>();

        // drzewoStringow.insert("Maciek");
        // drzewoStringow.insert("jebać");
        // drzewoStringow.insert("huj");
        // drzewoStringow.insert("insert");
        // drzewoStringow.insert("abba");

        // drzewoStringow.draw();

        // drzewoStringow.delete("insert");

        // drzewoStringow.draw();

        // ConsoleHandler<Integer> ch = new ConsoleHandler<Integer>(new ParserInt());
        // ch.run();

        HashMap<String, ConsoleHandler<?>> consoleHandlersMap = new HashMap<String, ConsoleHandler<?>>()
        {{
            put("String", new ConsoleHandler<String>(new ParserString()));
            put("Integer", new ConsoleHandler<Integer>(new ParserInt()));
            put("Double", new ConsoleHandler<Double>(new ParserDouble()));
        }};

        consoleHandlersMap.get(args[0]).run();
    }
}
