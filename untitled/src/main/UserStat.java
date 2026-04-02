package main;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class UserStat implements Serializable {
    private GameStat gameStat5;
    private GameStat gameStat6;
    private GameStat gameStat7;
    private GameStat gameStatAll;
    private transient StatManager statManager;
    @Serial
    private static final long serialVersionUID = 1L;

    public UserStat() {
        gameStat5 = new GameStat();
        gameStat6 = new GameStat();
        gameStat7 = new GameStat();
        gameStatAll = new GameStat();
        this.statManager = new StatManager();
    }

    public void finishGame(int length, boolean win, int countAnswer) {
        switch (length) {
            case 5: {
                gameStat5.changeStat(win, countAnswer);
                break;
            }
            case 6: {
                gameStat6.changeStat(win, countAnswer);
                break;
            }
            case 7: {
                gameStat7.changeStat(win, countAnswer);
                break;
            }
        }
        gameStatAll.changeStat(win, countAnswer);
    }

    public String seeStat(int type) {
        String answer;
        switch (type) {
            case 5: {
                answer = "Статистика по словам в 5 букв\n" + gameStat5.getStat();
                break;
            }
            case 6: {
                answer = "Статистика по словам в 6 букв\n" + gameStat6.getStat();
                break;
            }
            case 7: {
                answer = "Статистика по словам в 7 букв\n" + gameStat7.getStat();
                break;
            }
            case 0: {
                answer = "Статистика по всем словам\n" + gameStatAll.getStat();
                break;
            }
            default: {
                answer = "";
                break;
            }
        }
        return answer;
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
            this.gameStat5 = loadedStat.gameStat5;
            this.gameStat6 = loadedStat.gameStat6;
            this.gameStat7 = loadedStat.gameStat7;
            this.gameStatAll = loadedStat.gameStatAll;
        }
    }

    public String reset(int type) {
        String answer;
        switch (type) {
            case 5: {
                gameStatAll.changeStat(gameStat5.getCountGame(), gameStat5.getCountWinGame(), gameStat5.getRecord());
                gameStat5.resetStat();
                answer = "словам из 5 букв";
                break;
            }
            case 6: {
                gameStatAll.changeStat(gameStat6.getCountGame(), gameStat6.getCountWinGame(), gameStat6.getRecord());
                gameStat6.resetStat();
                answer = "словам из 6 букв";
                break;
            }
            case 7: {
                gameStatAll.changeStat(gameStat7.getCountGame(), gameStat7.getCountWinGame(), gameStat7.getRecord());
                gameStat7.resetStat();
                answer = "словам из 7 букв";
                break;
            }
            case 0: {
                gameStatAll.getStat();
                answer = "всем словам";
                break;
            }
            default: {
                answer = "";
                break;
            }
        }
        return "статистика по " + answer + " сброшена";
    }


    @Serial
    private void readObject(java.io.ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.statManager = new StatManager();
    }
}
