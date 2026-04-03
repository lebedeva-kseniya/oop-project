package main;

import java.io.Serializable;

import static java.lang.Math.min;

public class GameStat implements Serializable {
    private int countGame;
    private int countWinGame;
    private int record;
    private String name;
    private static final long serialVersionUID = 1L;

    public GameStat(String name)
    {
        countGame = 0;
        countWinGame = 0;
        record = Integer.MAX_VALUE;
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public int getCountGame() {
        return countGame;
    }

    public int getCountWinGame() {
        return countWinGame;
    }

    public int getRecord() {
        return record;
    }

    public void changeStat(boolean win, int countAnswer) {
        countGame++;
        if (win)
            countWinGame++;
        record = min(record, countAnswer);
    }

    public void changeStat(int countGame, int countWinGame, int record) {
        this.countGame = Math.max(0, this.countGame - countGame);
        this.countWinGame = Math.max(0, this.countWinGame - countWinGame);
        if (record == this.record) {
            this.record = Integer.MAX_VALUE;
        }
    }

    public String getStat() {
        return "кол-во игр всего: " + countGame + "\nкол-во выиграных игр: " + countWinGame + "\nрекорд:" + record+"\n";
    }

    public void resetStat() {
        countGame = 0;
        countWinGame = 0;
        record = Integer.MAX_VALUE;
    }
}
