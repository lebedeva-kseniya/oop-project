public class Logic_bot {
    public static boolean logic_start_game (String answer){
        return (answer.contains("игра") && !answer.contains("как"));
    }
    public static String logic_work(String answer){
        StringBuilder result=new StringBuilder();
        switch (answer){
            case "правила":
                result.append("Компьютер загадывает слово из 5 букв \n Вы вводите слова и видите буквы на своих местах");
                break;
            default:
                result.append("неизвестная команда");
                break;
        }
        return result.toString();
    }
}
