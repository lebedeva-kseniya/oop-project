import java.util.Random;

public class Storage extends StorageOfWords{
    public static String get_word(){
        Random random=new Random();
        return WORDS[random.nextInt(count_words)];
    }
}
