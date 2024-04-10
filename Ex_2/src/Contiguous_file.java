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

        for(int i=0; i<no; i++){
            System.out.println("\n"+(i+1));
            System.out.print("Enter the name of the file : ");
            name[i] = s.next();
            System.out.print("Enter the size of the file : ");
            file[i] = s.nextInt();
        }

        int size = full;
        for (int i=0; i<no; i++){
            if (file[i] <= full){
                System.out.println("File "+name[i]+" is allocated to the disk");
                full = full - file[i];
            } else {
                System.out.println("Storage Error!!!! File "+name[i]+" is not allocated to the disk");
            }
        }

        full = 0;

        System.out.println("\nFile name \t\t\t File size \t\t\t Starting Address \t\t\t Ending Address");
        for(int i=0; i<no; i++){
            System.out.println(name[i]+"\t\t\t\t\t"+file[i]+"\t\t\t\t\t"+full+"\t\t\t\t\t"+(full+file[i]));
            full = full + file[i];
        }

        System.out.println("\nRemaining space in the disk is : "+(size - full));
    }

}
