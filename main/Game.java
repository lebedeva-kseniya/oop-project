public class Game {
    private boolean game_end;
    private int count_answer;
    private String word;
    public Game(){
        count_answer=0;
        game_end=false;
    }
    private String normalised(String answer){
        return answer.toUpperCase();
    }
    public String game_start(){
        word=Storage.get_word();//выбор слова из хранилища
        count_answer=0;
        game_end=false;
        return "Игра началась!";
    }
    public String game_play(String answer){
        String normal_answer=normalised(answer);
        if (normal_answer.length() != 5 || !normal_answer.matches("[А-ЯЁ]+")) {
            return "Ошибка! Введите слово из 5 русских букв.";
        }
        count_answer++;
        if (normal_answer.equals(word)){
            game_end=true;
            return ("Побеба! Отгадано с " + count_answer + " попытки. Загаданное слово: " + word);
        }
        StringBuilder result = new StringBuilder();
        StringBuilder used_symbols = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (normal_answer.charAt(i) == word.charAt(i)) {
                result.append(normal_answer.charAt(i));
            } else {
                if (word.indexOf(normal_answer.charAt(i))>=0){
                    used_symbols.append(normal_answer.charAt(i));
                }
                result.append("_");
            }
        }
        return  (count_answer-1 + " Результат: " + result + " И есть буквы " +used_symbols);
    }
    public boolean is_game_end(){
        return game_end;
    }
}
