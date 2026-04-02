package main;

import java.time.LocalTime;

public class Game {
    private UserStat userStat;
    private boolean gameEnd;
    private int countAnswer;
    private String word;
    private int length;
    private int level;
    private final Storage storage;
    private boolean[] correctLetter = new boolean[7];
    private final Hint hint;
    private int countHint;
    private int mode;//3 -none 1-classic 0 -timer
    private LocalTime timeEnd;
    private int countWinWords;

    public Game(UserStat userStat) {
        countAnswer = 0;
        gameEnd = false;
        length = 0;
        storage = new Storage();
        level = 0;
        countHint = 0;
        hint = new Hint();
        this.userStat = userStat;
        mode=3;
    }

    private void gameEnd(boolean win) {
        userStat.finishGame(length, win, countAnswer);
        countAnswer = 0;
        gameEnd = true;
        word = null;
        if (mode==1){
            mode=3;
            length = 0;
            level = 0;
            countHint = 0;
        }
        for (int i = 0; i < 7; i++)
            correctLetter[i] = false;
    }

    public int levelWord() {
        return length;
    }

    public int level() {
        return level;
    }

    public boolean timeIsEnd(){
        return (LocalTime.now().isAfter(timeEnd));
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    private String normalised(String answer) {
        return answer.toUpperCase();
    }

    public String setDifficultWord(String answer) {
        switch (answer) {
            case "5" -> length = 5;
            case "6" -> length = 6;
            case "7" -> length = 7;
            default -> {
                return "Введите корректную длину слова";
            }
        }
        return "Сложность выбрана";
    }
    public int getMode(){return mode;}
    public String setModeGame(String answer){
        switch (answer) {
            case "0": {
                mode=0;
                countWinWords=0;
                break;
            }
            case "1": {
                mode=1;
                break;
            }
            case "2": {
                mode=0;
                length=5;
                countHint=0;
                level=5;
                countWinWords=0;
                break;
            }
            default: {
                return "Введите корректный режим игры";
            }
        }
        return "режим игры выбран";
    }
    public String setDifficult(String answer) {
        switch (answer) {
            case "0": {
                level = -1;
                countHint = 3;
                break;
            }
            case "1": {
                level = 20;
                countHint = 2;
                break;
            }
            case "2": {
                level = 10;
                countHint = 1;
                break;
            }
            default: {
                return "Введите корректный уровень сложности";
            }
        }
        return "Сложность выбрана";
    }

    public String gameStart() {
        countAnswer = 0;
        gameEnd = false;
        if (length == 5)
            word = storage.getWord5();//выбор слова из хранилища
        else
            word = storage.getWordFromWeb(length);
        if (word == null) {
            length = 0;
            gameEnd = true;
            return "Слово не может быть загружено, попробуйте снова";
        }
        if (mode==0)
            timeEnd=LocalTime.now().plusMinutes(5);
        return "Игра началась!";
    }
    public String Play(String answer){
        if (mode==1)
            return gamePlay(answer);
        else if (LocalTime.now().isBefore(timeEnd))
            return gamePlay(answer);
        mode=3;
        return "время вышло отгадано: "+countWinWords+" слов";
    }
    private String gamePlay(String answer) {
        if (answer.contains("/hint")) {
            return gamePlayHint(answer);
        } else if (answer.contains("/endgame")) {
            String Word = word;
            gameEnd(false);
            PhraseProvider provider = new PhraseProvider();
            return provider.getRandomEnding() + " загадано было: " + Word;
        } else if (level == -1)
            return gamePlayWord(answer);
        else if (countAnswer < level)
            return gamePlayWord(answer);
        else {
            String Word = word;
            gameEnd(false);
            return "К сожалению, попытки закончились, загадано было: " + Word;
        }
    }

    private String gamePlayHint(String answer) {
        if (countHint == 0)
            return "К сожалению подсказки закончились";
        else {

            if (answer.contains("/hint.random")) {
                countHint--;
                return hint.hintRandom(correctLetter, word, length);
            } else if (answer.contains("/hint.letter")) {
                countHint--;
                return hint.hintLetter(word, length);
            } else if (answer.contains("/hint")) {
                return hint.hint();
            } else return "неверная команда подсказки. Для справки по подсказкам введите /hint";
        }
    }

    public String gamePlayWord(String answer) {
        String normalAnswer = normalised(answer);
        if (normalAnswer.length() != length || !normalAnswer.matches("[А-ЯЁ]+")) {
            return "Ошибка! Введите слово из " + length + " русских букв.";
        }
        countAnswer++;
        if (normalAnswer.equals(word)) {
            String Word = word;
            int countA=countAnswer;
            gameEnd(true);
            countWinWords++;
            PhraseProvider provider = new PhraseProvider();
            return provider.getRandomWinPhrase(Word, countA);
        }
        StringBuilder result = new StringBuilder();
        StringBuilder usedSymbols = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (normalAnswer.charAt(i) == word.charAt(i)) {
                result.append(normalAnswer.charAt(i));
                correctLetter[i] = true;
            } else {
                if (word.indexOf(normalAnswer.charAt(i)) >= 0) {
                    usedSymbols.append(normalAnswer.charAt(i));
                }
                result.append("_");
            }
        }

        PhraseProvider provider = new PhraseProvider();
        return provider.getAttemptPhrase(countAnswer, result, usedSymbols);

    }
}
