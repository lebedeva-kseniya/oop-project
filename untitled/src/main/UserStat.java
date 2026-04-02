package main;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class UserStat implements Serializable {
    private GameStat[] gameStats;
    private transient StatManager statManager;
    @Serial
    private static final long serialVersionUID = 1L;

    public UserStat() {
        gameStats=new GameStat[8];
        gameStats[5] = new GameStat("словам 5 букв");
        gameStats[6] = new GameStat("словам 6 букв");
        gameStats[7] = new GameStat("словам 7 букв");
        gameStats[0] = new GameStat("всем словам");
        this.statManager = new StatManager();
    }

    public void finishGame(int length, boolean win, int countAnswer) {
        gameStats[length].changeStat(win, countAnswer);
        gameStats[0].changeStat(win, countAnswer);
    }

    public String seeStat(int type) {
        String answer;
        answer=gameStats[type].getName()+gameStats[type].getStat();
        return "Статистика по "+answer;
    }

    public void save() {
        if (statManager != null) {
            statManager.save(this);
        } else {
            System.err.println("statManager не инициализирован!");
        }
    }

    public void load() {
        UserStat loadedStat = statManager.load();
        if (loadedStat != null) {
            this.gameStats[5] = loadedStat.gameStats[5];
            this.gameStats[6] = loadedStat.gameStats[6];
            this.gameStats[7] = loadedStat.gameStats[7];
            this.gameStats[0] = loadedStat.gameStats[0];
        }
    }

    public String reset(int type) {
        String answer;
        gameStats[0].changeStat(gameStats[type].getCountGame(), gameStats[type].getCountWinGame(), gameStats[type].getRecord());
        gameStats[type].resetStat();
        answer = gameStats[type].getName();
        return "статистика по " + answer + " сброшена";
    }


    @Serial
    private void readObject(java.io.ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.statManager = new StatManager();
    }
}
