package main;

public class GameSession {
    private boolean isPlaying;
    private final Console console;
    private final LogicBot logicBot;
    private final PhraseProvider provider;
    private Game game;
    private final UserStat userStat;
    private boolean gameJustEnded;

    public GameSession(UserStat userStat, Console console) {
        this.isPlaying = false;
        this.console = console;
        this.userStat = userStat;
        this.userStat.load();
        this.logicBot = new LogicBot(userStat);
        this.provider = new PhraseProvider();
        this.gameJustEnded = false;
    }

    public String processInput(String answer) {
        gameJustEnded = false;

        if (!isPlaying) {
            if (logicBot.canStartGame(answer)) {
                StringBuilder response = new StringBuilder();
                response.append("Как ты хочешь играть?\n");
                response.append("1 - классический режим\n");
                response.append("0 - на время\n");
                response.append("2 - на время со стандартными настройками\n");
                response.append("Ответь цифрой:");
                return response.toString();
            } else if (answer.matches("[012]") && game != null && game.getMode() == 3) {
                String modeResult = game.setModeGame(answer);
                if (!modeResult.equals("режим игры выбран")) {
                    return modeResult;
                }
                return "Какое слово ты хочешь отгадывать? Из 5, 6, или 7 букв? Ответь цифрой";
            } else if (game != null && game.getMode() != 3 && game.levelWord() == 0 && answer.matches("[5-7]")) {
                String levelResult = game.setDifficultWord(answer);
                if (!levelResult.equals("Сложность выбрана")) {
                    return levelResult;
                }
                return "На какой сложности ты хочешь играть?\n0 - простая\n1 - средняя\n2 - сложная\nОтветь цифрой";
            } else if (game != null && game.getMode() != 3 && game.levelWord() != 0 && game.level() == 0 && answer.matches("[012]")) {
                String diffResult = game.setDifficult(answer);
                if (!diffResult.equals("Сложность выбрана")) {
                    return diffResult;
                }
                String startResult = game.gameStart();
                if (!game.isGameEnd()) {
                    isPlaying = true;
                }
                return startResult;
            } else {
                return logicBot.handleUserAnswer(answer);
            }
        } else {
            String result = game.Play(answer);
            if (game.isGameEnd()) {
                isPlaying = false;
                gameJustEnded = true;
                if (game.getMode() == 3 || game.timeIsEnd()) {
                    userStat.save();
                }
                if (game.getMode() != 3) {
                    String startResult = game.gameStart();
                    if (!game.isGameEnd()) {
                        isPlaying = true;
                        return result + "\n\n" + startResult;
                    }
                }
            }
            return result;
        }
    }

    public UserStat getUserStat() {
        return userStat;
    }

    public boolean isGameJustEnded() {
        return gameJustEnded;
    }
}