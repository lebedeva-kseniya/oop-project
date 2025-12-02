public class Game {
    private boolean gameEnd;
    private int countAnswer;
    private String word;
    private int length;
    private Storage storage;
    public Game(){
        countAnswer=0;
        gameEnd=false;
        length=0;
        storage=new Storage();
    }
    private void gameEnd(){
        gameEnd=true;
        length=0;
        word=null;
    }


    public int level(){return length;}
    public boolean isGameEnd(){
        return gameEnd;
    }
    private String normalised(String answer){
        return answer.toUpperCase();
    }
    public String setDifficult(String answer){
        if (answer.equals("5"))
            length=5;
        else if (answer.equals("6"))
            length=6;
        else if (answer.equals("7"))
            length=7;
        else return "Введите корректную длину слова";
        return "Сложность выбрана";
    }
    public String gameStart(){
        countAnswer=0;
        gameEnd=false;
        if (length==5)
            word=storage.getWord5();//выбор слова из хранилища
        else if (length==6){
            word=storage.getWord6();
            if (word==null){
                length=0;
                gameEnd=true;
                return "Слово не может быть загружено, попробуйте снова";
            }
        }
        else {
            word=storage.getWord7();
            if (word==null){
                length=0;
                gameEnd=true;
                return "Слово не может быть загружено, попробуйте снова";
            }
        }
        return "Игра началась!";
    }
    public String gamePlay(String answer){
        if (answer.contains("/endgame")){
            String Word=word;
            gameEnd();
            PhraseProvider provider = new PhraseProvider();
            return provider.getRandomEnding()+"загадагл было: "+Word;
        }
        String normalAnswer=normalised(answer);
        if (normalAnswer.length() != length || !normalAnswer.matches("[А-ЯЁ]+")) {
            return "Ошибка! Введите слово из "+ length + " русских букв.";
        }
        countAnswer++;
        if (normalAnswer.equals(word)){
            String Word=word;
            gameEnd();
            PhraseProvider provider = new PhraseProvider();
            return provider.getRandomWinPhrase(Word, countAnswer);

        }
        StringBuilder result = new StringBuilder();
        StringBuilder usedSymbols = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (normalAnswer.charAt(i) == word.charAt(i)) {
                result.append(normalAnswer.charAt(i));
            } else {
                if (word.indexOf(normalAnswer.charAt(i))>=0){
                    usedSymbols.append(normalAnswer.charAt(i));
                }
                result.append("_");
            }
        }

        PhraseProvider provider = new PhraseProvider();
        return provider.getAttemptPhrase(countAnswer, result, usedSymbols);

    }
}
