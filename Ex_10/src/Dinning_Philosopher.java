import java.util.Scanner;


public class Dinning_Philosopher {

    private static final int MAX_PHILOSOPHERS = 20;

    public static void allowOneToEat(int[] hu, int howhung, int[] philname) {
        System.out.println("\nAllow one philosopher to eat at any time");
        for (int i = 0; i < howhung; ++i) {
            System.out.print("\nP " + philname[hu[i]] + " is granted to eat");
            for (int j = i + 1; j < howhung; ++j)
                System.out.print("\nP " + philname[hu[j]] + " is waiting");
        }
    }

    public static void allowTwoToEat(int[] hu, int howhung, int[] philname) {
        System.out.println("\nAllow two philosophers to eat at the same time");
        for (int i = 0; i < howhung; ++i) {
            for (int j = i + 1; j < howhung; ++j) {
                if (Math.abs(hu[i] - hu[j]) >= 1 && Math.abs(hu[i] - hu[j]) != 4) {
                    System.out.println("\nP " + philname[hu[i]] + " and P " + philname[hu[j]] + " are granted to eat");
                    for (int x = 0; x < howhung; ++x) {
                        if (hu[x] != hu[i] && hu[x] != hu[j])
                            System.out.print("\nP " + philname[hu[x]] + " is waiting");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tph, howhung;
        System.out.println("\n\nDINING PHILOSOPHER PROBLEM");
        System.out.print("Enter the total number of philosophers: ");
        tph = scanner.nextInt();

        if (tph > MAX_PHILOSOPHERS) {
            System.out.println("Maximum number of philosophers exceeded. Exiting.");
            return;
        }

        int[] philname = new int[MAX_PHILOSOPHERS];
        int[] hu = new int[MAX_PHILOSOPHERS];

        for (int i = 0; i < tph; ++i) {
            philname[i] = (i + 1);
        }

        System.out.print("How many are hungry: ");
        howhung = scanner.nextInt();

        if (howhung == tph) {
            System.out.println("\nAll are hungry..\nDeadlock stage will occur\nExiting");
            return;
        }

        for (int i = 0; i < howhung; ++i) {
            System.out.print("Enter philosopher " + (i + 1) + " position: ");
            hu[i] = scanner.nextInt();
        }

        int choice;
        do {
            System.out.print("1. One can eat at a time\t2. Two can eat at a time\n3. Exit\nEnter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    allowOneToEat(hu, howhung, philname);
                    break;
                case 2:
                    allowTwoToEat(hu, howhung, philname);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nInvalid option\n");
            }
        } while (true);
    }
}