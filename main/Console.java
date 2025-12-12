import java.util.Scanner;

public class Console {
    private final Scanner input=new Scanner(System.in);
    public void dataOut(String data){
        System.out.println(data);//вывод данных в консоль
    }
    public String dataInput(){
        return input.nextLine().trim();//считывание данных с консоли
    }
}
