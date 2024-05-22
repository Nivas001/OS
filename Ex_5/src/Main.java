import java.util.*;
public class Main {

    public static void main(String[] args) {
        int[][] max;
        int[][] allocation;
        int[][] need_matric;
        int[] available;
        int n,r;
        Scanner obj=new Scanner(System.in);
        System.out.println("enter the no of proceses");
        n=obj.nextInt();
        System.out.println("enter the no of resources");
        r=obj.nextInt();

        max = new int[n][r];
        allocation = new int[n][r];
        need_matric = new int[n][r];
        available = new int[r];

        System.out.println("Enter the allocation");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<r;j++)
            {
                System.out.println(i +" "+j+" :");
                allocation[i][j] = obj.nextInt();
            }
        }

        System.out.println("Enter the max");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<r;j++)
            {
                max[i][j] = obj.nextInt();
            }
        }

        System.out.println("Enter the available resources:");
        for (int i = 0; i < r; i++) {
            available[i] = obj.nextInt();
        }

        // Calculate need matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                need_matric[i][j] = max[i][j] - allocation[i][j];
            }
        }

        System.out.println("Need Matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(need_matric[i][j]);

            }
            System.out.println("  ");
        }



        boolean[] finish = new boolean[n];
        int[] safeSeq = new int[n];
        int count = 0;
        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    boolean needLessThanOrEqualAvailable = true;
                    // Check if need matrix is less than or equal to available resources
                    for (int j = 0; j < r; j++) {
                        if (need_matric[i][j] > available[j]) {
                            needLessThanOrEqualAvailable = false;
                            break;
                        }
                    }
                    if (needLessThanOrEqualAvailable) {
                        // Allocate resources to process i
                        for (int j = 0; j < r; j++) {
                            available[j] += allocation[i][j];
                        }
                        // Mark process i as finished
                        finish[i] = true;
                        // Add process i to the safe sequence
                        safeSeq[count++] = i;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is not in a safe state");
                break;
            }
        }

        if (count == n) {
            System.out.println("System is in a safe state");
            System.out.print("Safe sequence is: ");
            for (int i = 0; i < n - 1; i++) {
                // Change "P" + safeSeq[i] to "P" + (safeSeq[i] + 1)
                System.out.print("P" + (safeSeq[i] + 1) + " -> ");
            }
            // Change "P" + safeSeq[n - 1] to "P" + (safeSeq[n - 1] + 1)
            System.out.println("P" + (safeSeq[n - 1] + 1));
        }
    }
}