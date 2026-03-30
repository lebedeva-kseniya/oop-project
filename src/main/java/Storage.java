import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Storage extends StorageOfWords {

    public String getWord5() {
        Random random = new Random();
        return StorageOfWords.WORDS[random.nextInt(StorageOfWords.countWords)];
    }

    public String getWord6() {
        return getWordFromWeb(6);
    }

    public String getWord7() {
        return getWordFromWeb(7);
    }

    public String getWordFromWeb(int length) {
        String url;
        if (length == 6)
            url = "https://sanstv.ru/randomWord/lang-ru/strong-2/count-1/word-%3F%3F%3F%3F%3F%3F";
        else
            url = "https://sanstv.ru/randomWord/lang-ru/strong-2/count-1/word-%3F%3F%3F%3F%3F%3F%3F";

        try {
            Document doc = Jsoup.connect(url).get();
            String html = doc.toString();
            int index = html.indexOf("target=\"_blank \"");

            if (index == -1) return null;

            index += 17;
            return html.substring(index, index + length).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }
}