public class Bot{
    //private User user; //for future
    private boolean isPlaying;
    public Bot(){
        isPlaying=false;
    }
    public void botStart(){

        Console console=new Console();
        Game game=new Game();
        LogicBot logicBot=new LogicBot();

        PhraseProvider provider = new PhraseProvider();
        String greeting = provider.getRandomGreeting();
        console.dataOut(greeting);

        while (true){
            String answer=console.dataInput();
            if (!isPlaying){
                if (logicBot.canStartGame(answer)){//проверить на начинать ли игру
                    console.dataOut("Какое слово ты хочешь отгадывать? Из 5, 6, или 7 букв? ответь цифрой");//выбор уровня сложности
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
                console.dataOut(game.gamePlay(answer));
                if (game.isGameEnd()){
                    isPlaying=false;
                }
            }
        }
    }
}
