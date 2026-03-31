package main;

import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Storage extends StorageOfWords{
    private final String urlBase="https://sanstv.ru/randomWord/lang-ru/strong-2/count-1/word-%3F%3F%3F%3F%3F%3F";
    public String getWord5(){
        Random random=new Random();
        return WORDS[random.nextInt(countWords)];
    }
    public  String getWordFromWeb(int length){
        String url;
        if (length==7)
            url=urlBase+"%3F";
        else url=urlBase;
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            int index=doc.toString().indexOf("target=\"_blank \"")+17;
            return doc.toString().substring(index,index+length).toUpperCase();
        } catch (Exception e){
            return null;
        }
    }
}
