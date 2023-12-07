package L1;

import java.util.Scanner;

public class L1_3 {
    public static void  main(String[] args) {
        int x;
        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть число: ");
        x = scan.nextInt();
        float y = x % 2;

        if(y == 0){
            System.out.print("Число парне");
        } else {
            System.out.print("Число непарне");
        }

    }
}
