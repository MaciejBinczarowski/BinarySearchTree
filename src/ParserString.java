public class ParserString implements Parseable<String>
{

    @Override
    public String parse(String stringValue) 
    {
        return stringValue.toString();
    }
    
}
