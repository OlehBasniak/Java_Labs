package L1;

public class L1_2 {
    public static void  main(String[] args) {
        String text = "Well, maybe I don’t need your money. Wait, wait, I said maybe!";
        String substring1 = "maybe", substring2 = "apple";;

        int stringLength = text.length();
        System.out.println("Довжина рядка: " + stringLength);

        boolean containsSubstring1 = text.contains(substring1);
        System.out.println("Рядок містить послідовність символів '" + substring1 + "': " + containsSubstring1);

        boolean containsSubstring2 = text.contains(substring2);
        System.out.println("Рядок містить послідовність символів '" + substring2 + "': " + containsSubstring2);

        String vowelsOnly = text.replaceAll("[^aeiouAEIOU ]", "");
        System.out.println("Рядок без приголосних: " + vowelsOnly);

        String resultString = text + " " + vowelsOnly;
        System.out.println("Результат після додавання: " + resultString);

    }
}
