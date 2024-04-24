import java.util.*;

public class Contiguous_file {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int no, full;

        //To get the size of the disk and the number of files
        System.out.print("Enter the size of the disk: ");
        full = s.nextInt();
        System.out.print("Enter the number of files: ");
        no = s.nextInt();

        //Creating a Array of files using the no. of files
        int[] file = new int[no];
        String[] name = new String[no];
        int[] start = new int[no];
        int[] fully = new int[no];

        for(int i=0; i<no; i++){
            System.out.println("\n"+(i+1));
            System.out.print("Enter the name of the file : ");
            name[i] = s.next();
            System.out.print("Enter the size of the file : ");
            file[i] = s.nextInt();
            System.out.println("Enter the start of the file : ");
            start[i] = s.nextInt();

        }

        int size = full;
        for (int i=0; i<no; i++){
            if (file[i] <= full){
                fully[i] = start[i] + file[i];
                System.out.println("File "+name[i]+" is allocated to the disk");
                full = full - file[i];
            } else {
                System.out.println("Storage Error!!!! File "+name[i]+" is not allocated to the disk");
            }
        }


        int total = 0;
        System.out.println("\nFile name \t\t\t File size \t\t\t Starting Address \t\t\t Ending Address");
        for(int i=0; i<no; i++) {
            if (file[i] <= size) {
                System.out.println(name[i] + "\t\t\t\t\t" + file[i] + "\t\t\t\t\t" + start[i] + "\t\t\t\t\t\t\t" + (fully[i]));
                size = size - file[i];
                total += file[i];
            } else {
                System.out.println(name[i] + "\t\t\t\t\t" + file[i] + "\t\t\t\t\t" + "Not Allocated" + "\t\t\t\t\t" + "Not Allocated");
            }
        }
        total = total - size;

        System.out.println("\nRemaining space in the disk is : "+total);
    }

}
