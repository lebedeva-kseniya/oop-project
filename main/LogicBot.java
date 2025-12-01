public class LogicBot {
    public boolean canStartGame (String answer){
        return (answer.contains("игра"));
    }
    public String handleUserAnswer(String answer){
        StringBuilder result=new StringBuilder();
        switch (answer){
            case "/help":
                result.append("Компьютер загадывает слово \n Вы вводите слова и видите буквы на своих местахи и те которые есть в слове \n для начала игры введите \"игра\" \n для выхода из игры введите \"/end game\"");
                break;
            default:
                result.append("неизвестная команда");
                break;
        }
        return result.toString();
    }
}
