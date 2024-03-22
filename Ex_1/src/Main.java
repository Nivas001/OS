import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] remainingTime = new int[n];

        System.out.println("Enter the burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = scanner.nextInt();
            remainingTime[i] = burstTime[i];
        }

        System.out.print("Enter the time quantum: ");
        int quantum = scanner.nextInt();

        int currentTime = 0;
        boolean[] completed = new boolean[n];

        while (true) {
            boolean allCompleted = true;
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0) {
                    allCompleted = false;

                    if (remainingTime[i] > quantum) {
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        currentTime += remainingTime[i];
                        waitingTime[i] = currentTime - burstTime[i];
                        remainingTime[i] = 0;
                        completed[i] = true;
                    }
                }
            }

            if (allCompleted) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }

        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            averageWaitingTime += waitingTime[i];
            averageTurnaroundTime += turnaroundTime[i];
        }
        averageWaitingTime /= n;
        averageTurnaroundTime /= n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        scanner.close();
    }
}
