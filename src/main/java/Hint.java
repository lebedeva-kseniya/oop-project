import java.util.Random;

public class Hint {
    public String hint(){
        return "Подсказки помогают выбраться из сложной ситуации,\n/hint.random покажет букву на случайной, ранее не открытой позиции\n/hint.letter выведет 2 случайные буквы из слова";
    }
    public String hintRandom(boolean[] correctLetter,String word,int length){
        boolean flag=false;
        for(int i=0;i<length;i++){
            if (!correctLetter[i]) {
                flag = true;
                break;
            }
        }
        if (!flag)
            return "Буквы на всех позициях были отгаданы";
        Random random=new Random();
        while (true){
        int index=random.nextInt(length);
        if (!correctLetter[index]){
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < length; i++) {
                if (index ==i) {
                    answer.append(word.charAt(index));
                } else {
                    answer.append("_");
                }
            }
            return answer.toString();
        }
        }
    }
    public String hintLetter(String word,int length){
        Random random=new Random();
        int index1= random.nextInt(length);
        int index2= random.nextInt(length);
        while (index2==index1)
            index2= random.nextInt(length);
        return "В слове есть буквы: "+word.charAt(index1)+","+word.charAt(index2);
    }


}
