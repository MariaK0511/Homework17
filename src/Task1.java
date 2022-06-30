import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void main(String[] args) {
        /*
        1. Написать программу для проверки на валидность введенного ір адреса.
Пусть ір адрес задается с консоли.
Программа должна проверять валидность введенного іp адреса с помощью регулярного выражения
и выводить результат проверки на экран.
         */
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String ipCheck = "\\d{1,4}+\\.+";
        Pattern pattern = Pattern.compile(ipCheck);
        Matcher matcher = pattern.matcher(ip);
        boolean b = matcher.find();
        System.out.println(b);
    }
}
