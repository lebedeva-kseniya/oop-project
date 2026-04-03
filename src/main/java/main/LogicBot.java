package main;

public class LogicBot {
    UserStat userStat;

    public LogicBot(UserStat userStat) {
        this.userStat = userStat;
    }

    public boolean canStartGame(String answer) {
        return (answer.contains("/play"));
    }

    public String handleUserAnswer(String answer) {
        StringBuilder result = new StringBuilder();
        if (answer.contains("/seeStat")) {
            switch (answer) {
                case "/seeStat5": {
                    result.append(userStat.seeStat(5));
                    break;
                }
                case "/seeStat6": {
                    result.append(userStat.seeStat(6));
                    break;
                }
                case "/seeStat7": {
                    result.append(userStat.seeStat(7));
                    break;
                }
                case "/seeStatAll": {
                    result.append(userStat.seeStat(0));
                    break;
                }
                default: {
                    result.append("неизвестная команда");
                    break;
                }
            }
        } else if (answer.contains("/resetStat")) {
            switch (answer) {
                case "/resetStat5": {
                    result.append(userStat.reset(5));
                    userStat.save();
                    break;
                }
                case "/resetStat6": {
                    result.append(userStat.reset(6));
                    userStat.save();
                    break;
                }
                case "/resetStat7": {
                    result.append(userStat.reset(7));
                    userStat.save();
                    break;
                }
                case "/resetStatAll": {
                    result.append(userStat.reset(0));
                    userStat.save();
                    break;
                }
                default: {
                    result.append("неизвестная команда");
                    break;
                }
            }
        } else
            switch (answer) {
                case "/help": {

                    result.append("УГАДАЙ СЛОВО\n");
                    result.append("1. Слово представлено как `_____`\n2. Вводи слова этой же длины\n3. Если буква на месте - она появится в слове\n4. Если буква есть, но не на своём месте - это будет указано рядом\n");
                    result.append("режимы игры: 1 - каждое слово отгадывается отдельно\n0 - отгадать как можно больше слов за 5 минут (слово и сложность настраивается в ручную)\n2 - аналогично режиу 0, но слово 5 букв, подсказок нет, отгадать слово надо за 5 попыток\n");
                    result.append("КОМАНДЫ:\n/play - начать\n/endgame - завершить\n/help - справка\n/hint - подсказка\n");
                    result.append("КОМАНДЫ:\n/seeStat - посмотреть статистику\n/resetStat- сбросить статистику\n/seeAllStat - вывод всей статистики\n/resetAllStat - полный сброс статистики\n");
                    result.append("для работы с конкретной статистикой в конце команды добавляется длина слова или All для статистики по всем играм\n");
                    result.append("Отгадай слово полностью!");

                    break;
                }
                case "/seeAllStat": {
                    result.append(userStat.seeStat(5));
                    result.append(userStat.seeStat(6));
                    result.append(userStat.seeStat(7));
                    result.append(userStat.seeStat(0));
                    break;
                }
                case "/resetAllStat": {
                    result.append(userStat.reset(5));
                    result.append(userStat.reset(6));
                    result.append(userStat.reset(7));
                    result.append(userStat.reset(0));
                    userStat.save();
                    break;
                }
                default:
                    result.append("неизвестная команда");
                    break;
            }
        return result.toString();
    }
}
