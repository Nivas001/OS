import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        // Declare single-dimensional arrays
        int[] processId = new int[numProcesses];
        int[] virtualAddress = new int[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            processId[i] = i + 1; // Assign process ID
            System.out.print("Enter the virtual address for Process " + processId[i] + ": ");
            virtualAddress[i] = scanner.nextInt(); // Assign virtual address
        }

        System.out.print("Enter the page size: ");
        int pageSize = scanner.nextInt();

        System.out.print("Enter the memory size: ");
        int memorySize = scanner.nextInt();

        // Call the simulatePaging method with the new arrays
        Object[] result = simulatePaging(processId, virtualAddress, pageSize, memorySize);
        int[] memory = (int[]) result[0];
        int pageFaults = (int) result[1];

        System.out.println("\nMemory Content:");
        for (int i = 0; i < memory.length; i++) {
            System.out.println("Page " + i + ": " + memory[i]);
        }
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    //return type is objext[]
    public static Object[] simulatePaging(int[] processId, int[] virtualAddress, int pageSize, int memorySize) {
        int numPages = memorySize / pageSize;
        int[] memory = new int[numPages];
        int pageFaults = 0;

        for (int i = 0; i < processId.length; i++) {
            int pageNumber = virtualAddress[i] / pageSize;

            if (pageNumber >= numPages) {
                System.out.println("Error: Virtual address " + virtualAddress[i] + " exceeds memory size.");
                continue;
            }

            if (memory[pageNumber] == 0) {
                memory[pageNumber] = processId[i];
                pageFaults++;
            }
        }
        return new Object[]{memory, pageFaults};
// here we are returning the memory of two one is memory array and anotherone is page fault count
    }
}