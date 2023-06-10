public class ParserDouble implements Parseable<Double>
{
    public Double parse(String stringValue) 
    {
        return Double.parseDouble(stringValue);
    }
}
