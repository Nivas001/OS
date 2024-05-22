import java.util.Scanner;
public class Page_replacement_algorithm {
    public static void fifo(int[] pages, int capacity) {
        int[] frame = new int[capacity];
        int pageFaults = 0;
        int pageHits = 0;
        int nextReplaceIndex = 0;

        for (int page : pages) {
            boolean pageFound = false;
            for (int i = 0; i < frame.length; i++) {
                if (frame[i] == page) {
                    pageFound = true;
                    pageHits++;
                    break;
                }
            }
            if (!pageFound) {
                if (nextReplaceIndex < capacity) {
                    frame[nextReplaceIndex] = page;
                    nextReplaceIndex++;
                } else {
                    nextReplaceIndex = 0;
                    frame[nextReplaceIndex] = page;
                }
                pageFaults++;
            }
        }
        System.out.println("Number of page faults using FIFO: " + pageFaults);
        System.out.println("Number of page hits using FIFO: " + pageHits);
    }

    public static void lru(int[] pages, int capacity) {
        int[] frame = new int[capacity];
        int[] recentUse = new int[capacity];
        int pageFaults = 0;
        int pageHits = 0;
        int nextReplaceIndex = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            boolean pageFound = false;
            for (int j = 0; j < frame.length; j++) {
                if (frame[j] == page) {
                    pageFound = true;
                    recentUse[j] = i;
                    pageHits++;
                    break;
                }
            }
            if (!pageFound) {
                if (nextReplaceIndex < capacity) {
                    frame[nextReplaceIndex] = page;
                    recentUse[nextReplaceIndex] = i;
                    nextReplaceIndex++;
                } else {
                    int lruIndex = 0;
                    for (int j = 1; j < capacity; j++) {
                        if (recentUse[j] < recentUse[lruIndex]) {
                            lruIndex = j;
                        }
                    }
                    frame[lruIndex] = page;
                    recentUse[lruIndex] = i;
                }
                pageFaults++;
            }
        }
        System.out.println("Number of page faults using LRU: " + pageFaults);
        System.out.println("Number of page hits using LRU: " + pageHits);
    }

    public static void optimal(int[] pages, int capacity) {
        int[] frame = new int[capacity];
        int pageFaults = 0;
        int pageHits = 0;
        int nextReplaceIndex = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            boolean pageFound = false;
            for (int j = 0; j < frame.length; j++) {
                if (frame[j] == page) {
                    pageFound = true;
                    pageHits++;
                    break;
                }
            }
            if (!pageFound) {
                if (nextReplaceIndex < capacity) {
                    frame[nextReplaceIndex] = page;
                    nextReplaceIndex++;
                } else {
                    int maxIndex = -1;
                    int farthest = -1;
                    for (int j = 0; j < frame.length; j++) {
                        int nextOccurrence = Integer.MAX_VALUE;
                        for (int k = i + 1; k < pages.length; k++) {
                            if (pages[k] == frame[j]) {
                                nextOccurrence = k;
                                break;
                            }
                        }
                        if (nextOccurrence > farthest) {
                            farthest = nextOccurrence;
                            maxIndex = j;
                        }
                    }
                    frame[maxIndex] = page;
                }
                pageFaults++;
            }
        }
        System.out.println("Number of page faults using Optimal: " + pageFaults);
        System.out.println("Number of page hits using Optimal: " + pageHits);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter page reference string separated by space: ");
        String[] input = scanner.nextLine().split(" ");
        int[] pages = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            pages[i] = Integer.parseInt(input[i]);
        }

        System.out.print("Enter number of frames: ");
        int capacity = scanner.nextInt();

        System.out.print("Choose page replacement algorithm:\n1. FIFO\n2. LRU\n3. Optimal\n");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                fifo(pages, capacity);
                break;
            case 2:
                lru(pages, capacity);
                break;
            case 3:
                optimal(pages, capacity);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}