import java.util.Scanner;

public class Paging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from the user
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        int[][] processes = new int[numProcesses][2];
        for (int i = 0; i < numProcesses; i++) {
            int processId = i + 1;
            System.out.print("Enter the virtual address for Process " + processId + ": ");
            int virtualAddress = scanner.nextInt();
            processes[i][0] = processId;
            processes[i][1] = virtualAddress;
        }

        System.out.print("Enter the page size: ");
        int pageSize = scanner.nextInt();

        System.out.print("Enter the memory size: ");
        int memorySize = scanner.nextInt();

        // Simulate Paging
        Object[] result = simulatePaging(processes, pageSize, memorySize);
        int[] memory = (int[]) result[0];
        int pageFaults = (int) result[1];

        // Print the results
        System.out.println("\nMemory Content:");
        for (int i = 0; i < memory.length; i++) {
            System.out.println("Page " + i + ": " + memory[i]);
        }
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    public static Object[] simulatePaging(int[][] processes, int pageSize, int memorySize) {
        int numPages = memorySize / pageSize;
        int[] memory = new int[numPages];
        int pageFaults = 0;

        for (int[] process : processes) {
            int processId = process[0];
            int virtualAddress = process[1];
            int pageNumber = virtualAddress / pageSize;

            if (pageNumber >= numPages) {
                System.out.println("Error: Virtual address " + virtualAddress + " exceeds memory size.");
                continue;
            }

            if (memory[pageNumber] == 0) {
                memory[pageNumber] = processId;
                pageFaults++;
            }
        }

        return new Object[]{memory, pageFaults};
    }
}