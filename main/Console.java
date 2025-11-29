import java.util.Scanner;

public class Console {
    private final Scanner input=new Scanner(System.in);
    public void data_out(String data){
        System.out.println(data);//вывод данных в консоль
    }
    public String data_input(){
        return input.nextLine().trim();//считывание данных с консоли
    }
}
