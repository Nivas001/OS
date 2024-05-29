import java.util.Scanner;

public class Paging_Technique {
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of process : ");
        int pro = s.nextInt();
        int[] pro_id = new int[pro];
        int[] vir_add = new int[pro];

        for(int i=0; i<pro; i++){
            pro_id[i] = i+1;
            System.out.print("Enter the virtual address for "+pro_id[i]+" : ");
            vir_add[i] = s.nextInt();
        }

        System.out.print("Enter the Page size : ");
        int page_size = s.nextInt();

        System.out.print("Enter the memory size : ");
        int memory_size = s.nextInt();

        Object[] result = simulate(pro_id, vir_add, page_size, memory_size);
        int[] memory = (int[]) result[0];
        int pageFault = (int) result[1];

        System.out.println("Memory Content ");
        for(int i=0; i<memory.length; i++){
            System.out.println("Page "+i +" : "+memory[i]);
        }
        System.out.println("PageFault : "+pageFault);
    }
    public static Object[] simulate(int[] pro_id, int[] vir_add, int page_size, int memory_size){
        int numPage = memory_size/page_size;
        int[] memory = new int[numPage];
        int pageFault =0;

        for(int i=0; i< pro_id.length; i++){
            int pageNumber = vir_add[i]/page_size;

            if(pageNumber >= numPage){
                System.out.println("Error in creating virtual address "+vir_add[i]+" exceeds memory.");
                continue;
            }

            if(memory[pageNumber]==0){
                memory[pageNumber] = pro_id[i];
                pageFault++;
            }

        }
        return new Object[] {memory, pageFault};

    }
}
