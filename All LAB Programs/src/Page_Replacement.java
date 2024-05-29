import java.util.Scanner;

public class Page_Replacement {
    public static void fifo(int[] pages, int capacity){
        int[] frame = new int [capacity];
        int pageFault = 0;
        int pageHit = 0;
        int pageReplace = 0;

        for(int page : pages){
            boolean pageFound = false;
            for(int j=0; j<capacity; j++){
                if(frame[j] == page){
                    pageFound = true;
                    pageHit++;
                }
            }

            if(!pageFound){
                if(pageReplace < capacity){
                    frame[pageReplace] = page;
                    pageReplace++;
                }
                else{
                    pageReplace = 0;
                    frame[pageReplace] = page;
                }
                pageFault++;
            }
        }
        System.out.println("Page Fault : "+pageFault);
        System.out.println("Page Hit : "+pageHit);
    }
    public static void lru(int[] pages, int capacity){
        int[] frame = new int[capacity];
        int[] recentUse = new int[capacity];
        int pageFault = 0;
        int pageHit = 0;
        int pageReplace = 0;

        for(int i=0; i<pages.length; i++){
            int page = pages[i];
            boolean pageFound = false;
            for(int j =0; j<capacity; j++){
                if(frame[j] == page){
                    pageFound = true;
                    pageHit++;
                    recentUse[j] = i;
                    break;
                }
            }
            if(!pageFound){
                if(pageReplace < capacity){
                    frame[pageReplace] = page;
                    recentUse[pageReplace] = i;
                    pageReplace++;
                }
                else{
                    int lru_index = 0;
                    for(int j=1; j<capacity; j++){
                        if(recentUse[lru_index] < recentUse[j]){
                            lru_index = j;
                        }
                    }
                    frame[lru_index] = page;
                    recentUse[lru_index] = i;
                }
                pageFault++;
            }
        }

        System.out.println("Page Fault : "+pageFault);
        System.out.println("Page Hit : "+pageHit);
    }
    public static void optimal(int[] pages, int capacity){
        int[] frame = new int [capacity];
        int pageFault = 0;
        int pageHit = 0;
        int pageReplace = 0;

        for(int i=0; i<pages.length; i++){
            int page = pages[i];
            boolean pageFound = false;
            for(int j = 0; j<capacity; j++){
                if(frame[j] == page){
                    pageFound = true;
                    pageHit++;
                }
            }
            if(!pageFound){
                if(pageReplace < capacity){
                    frame[pageReplace] = page;
                    pageReplace++;
                }
                else{
                    int max_index = -1;
                    int farthest = -1;
                    for(int j=0; j<capacity; j++){
                        int nextOccurrence = Integer.MAX_VALUE;
                        for(int k= i+1; k<pages.length; k++){
                            if(pages[k] == frame[j]){
                                nextOccurrence = k;
                                break;
                            }
                        }
                        if(nextOccurrence < farthest){
                            farthest = nextOccurrence;
                            max_index = j;
                        }
                    }
                    frame[max_index] = page;
                }
                pageFault++;
            }
        }
        System.out.println("Page Fault : "+pageFault);
        System.out.println("Page Hit : "+pageHit);
    }


    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        int pages[];
        String Character[];
        System.out.print("Enter the string with space in between :");
        Character = s.nextLine().split(" ");

        pages = new int[Character.length];
        for (int i=0; i< Character.length; i++){
            pages[i] = Integer.parseInt(Character[i]);
        }
        System.out.print("Enter the frame size : ");
        int capacity = s.nextInt();

        System.out.println("Choose Page Replacement Algorithm \n1. FIFO\n2. LRU\n3. Optimal");
        int choice = s.nextInt();

        switch(choice){
            case 1:
                fifo(pages, capacity);
                break;
            case 2:
                lru(pages, capacity);
                break;
            case 3:
                optimal(pages, capacity);
                break;
        }
    }
}
