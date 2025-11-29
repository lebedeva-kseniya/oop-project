public class Bot{
    //private User user; //for future
    private boolean is_playing;
    public Bot(){
        is_playing=false;
    }
    public void bot_start(){
        Console console=new Console();
        Game game=new Game();
        console.data_out("Привет! Давай играть.");
        while (true){
            String answer=console.data_input();
            if (!is_playing){
                if (Logic_bot.logic_start_game(answer)){//проверить на начинать ли игру
                    is_playing=true;
                    console.data_out(game.game_start());
                }
                else {
                console.data_out(Logic_bot.logic_work(answer));//обработка отальных вариантов ответа
                }
            }
            else{
                console.data_out(game.game_play(answer));
                if (game.is_game_end()){
                    is_playing=false;
                }
            }
        }
    }
}

