public class LogicBot {
    public boolean canStartGame (String answer){
        return (answer.contains("/play"));
    }
    public String handleUserAnswer(String answer){
        StringBuilder result=new StringBuilder();
        switch (answer){
            case "/help":

                result.append("УГАДАЙ СЛОВО\n");
                result.append("1. Слово представлено как `_____`\n2. Вводи слова этой же длины\n3. Если буква на месте - она появится в слове\n4. Если буква есть, но не на своём месте - это будет указано рядом\n");
                result.append("КОМАНДЫ:\n/play - начать\n/endgame - завершить\n/help - справка\n");
                result.append("Отгадай слово полностью!");

                break;
            default:
                result.append("неизвестная команда");
                break;
        }
        return result.toString();
    }
}
