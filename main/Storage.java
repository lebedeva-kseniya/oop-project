import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Storage extends StorageOfWords{
    public String getWord5(){
        Random random=new Random();
        return WORDS[random.nextInt(countWords)];
    }
    public  String getWord6(){
        String url="https://sanstv.ru/randomWord/lang-ru/strong-2/count-1/word-%3F%3F%3F%3F%3F%3F";
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            int index=doc.toString().indexOf("target=\"_blank \"")+17;
            return doc.toString().substring(index,index+6).toUpperCase();
        } catch (Exception e){
            return null;
        }
    }
    public  String getWord7(){
        String url="https://sanstv.ru/randomWord/lang-ru/strong-2/count-1/word-%3F%3F%3F%3F%3F%3F%3F";
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            int index=doc.toString().indexOf("target=\"_blank \"")+17;
            return doc.toString().substring(index,index+7).toUpperCase();
        } catch (Exception e){
            return null;
        }
    }
}
