public class ParserInt implements Parseable<Integer>
{
    public Integer parse(String stringValue) 
    {
        return Integer.parseInt(stringValue);
    }
}
