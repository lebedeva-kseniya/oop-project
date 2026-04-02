package main;

public class Bot{
    private boolean isPlaying;
    private Console console;
    private LogicBot logicBot;
    private PhraseProvider provider;
    private Game game;
    private UserStat userStat;

    public Bot(){
        isPlaying=false;
        console=new Console();
        userStat=new UserStat();
        userStat.load();
        logicBot=new LogicBot(userStat);
        provider = new PhraseProvider();
    }

    public void botStart(){
        String greeting = provider.getRandomGreeting();
        console.dataOut(greeting);
        game=new Game(userStat);
        while (true){
            String answer=console.dataInput();
            if (!isPlaying){
                if (logicBot.canStartGame(answer)){//проверить на начинать ли игру
                    console.dataOut("как ты хочешь играть? 1 - классический режим, 0 - на время, 2 - на время со стандартными настройками");//выбор режима
                    while (game.getMode()==3)
                        console.dataOut(game.setModeGame(console.dataInput()));
                    console.dataOut("Какое слово ты хочешь отгадывать? Из 5, 6, или 7 букв? ответь цифрой");//выбор лины слова
                    while (game.levelWord()==0)
                        console.dataOut(game.setDifficultWord(console.dataInput()));
                    console.dataOut("на какой сложности ты хочешь играть? 0 - простая, 1 - средняя, 2 - сложная, ответь цифрой");//выбор уровня сложности
                    while (game.level()==0)
                        console.dataOut(game.setDifficult(console.dataInput()));
                    console.dataOut(game.gameStart());
                    if (!game.isGameEnd())
                        isPlaying=true;
                }
                else {
                console.dataOut(logicBot.handleUserAnswer(answer));//обработка отальных вариантов ответа
                }
            }
            else{
                console.dataOut(game.Play(answer));
                if (game.isGameEnd()){
                    isPlaying=false;
                    if (game.getMode()==3 || game.timeIsEnd())
                        userStat.save();
                    if (game.getMode()!=3){
                        console.dataOut(game.gameStart());
                        if (!game.isGameEnd())
                            isPlaying=true;
                    }
                }
            }
        }
    }
}
