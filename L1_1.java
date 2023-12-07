package L1;

import java.util.Scanner;

public class L1_1 {
    public static void  main(String[] args) {
        int length, width, S, P;
        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть довжину: ");
        length = scan.nextInt();
        System.out.print("Введіть ширину: ");
        width = scan.nextInt();

        S = length * width;
        P = length + width;

        System.out.println("");
        System.out.println("Площа: " + S);
        System.out.println("Периметр: " + P);
    }
}
