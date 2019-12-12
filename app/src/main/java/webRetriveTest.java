import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class webRetriveTest{
    public static void main( String[] args ) throws IOException{
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        String title = doc.title();
        System.out.println("alegreya_family is: " + title);
    }
}