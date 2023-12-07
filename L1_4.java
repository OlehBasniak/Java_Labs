package L1;

import java.util.Scanner;

public class L1_4 {
    public static void  main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть перше число: ");
        int number1 = scan.nextInt();
        System.out.print("Введіть друге число: ");
        int number2 = scan.nextInt();

        System.out.print("Введіть дію: ");
        String action = scan.nextLine();
        action = scan.nextLine();

        int add, sub, mul, div;

        switch(action) {
            case "+":
                add = number1 + number2;
                System.out.println(number1 + " + " + number1 + " = " + add);
                break;
            case "-":
                sub = number1 - number2;
                System.out.println(number1 + " - " + number1 + " = " + sub);
                break;
            case "*":
                mul = number1 * number2;
                System.out.println(number1 + " + " + number1 + " = " + mul);
                break;
            case "/":
                if(number2 == 0){
                    System.out.println("Не діли на 0!");
                } else {
                    div = number1 / number2;
                    System.out.println(number1 + " / " + number1 + " = " + div);
                }
                break;
            default:
                System.out.println("Щось не те");
        }

    }
}