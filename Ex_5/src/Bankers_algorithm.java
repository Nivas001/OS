import java.util.Scanner;

class Getting_input{

    Scanner s = new Scanner(System.in);
    int res, pro;
    int[] avail_res;
    public String[] pro_name;
    int[][] max;
    int[][] allocation;
    int[][] need;


    void get_resource(){
        System.out.print("How many resource You need ? : ");
        res = s.nextInt();

        avail_res = new int[res];
        System.out.println("Enter the available resource for "+res+" :");
        for(int i=0; i<res; i++){
            avail_res[i] = s.nextInt();
        }
    }

    public void get_process(){
        System.out.print("How many process are running? : ");
        pro = s.nextInt();

        pro_name = new String[pro];
        System.out.println("Enter the names for all process :-");
        for (int i=0; i<pro; i++){
            pro_name[i] = s.next();
        }
    }

    void get_allocation(){
        this.allocation = new int[pro][res];
        System.out.println("Enter the allocation values :-");{
            for(int i=0; i<pro; i++){
                System.out.println("For Process "+this.pro_name[i]+" :-");
                for(int j=0; j<res; j++){
                    allocation[i][j] = s.nextInt();
                }
            }
        }
    }

    void get_max_allocation(){
        this.max = new int[pro][res];
        System.out.println("Enter the maximum demand values :-");{
            for(int i=0; i<pro; i++){
                System.out.println("For Process "+this.pro_name[i]+" :-");
                for(int j=0; j<res; j++){
                    max[i][j] = s.nextInt();
                }
            }
        }
    }

    void calculate_need_matrix(){
        this.need = new int[pro][res];
        for(int i=0; i<pro; i++){
            for(int j=0; j<res; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    void display(){
        System.out.println("Process Name\t\tAllocation\t\t\tMax\t\tNeed");
        for(int i=0; i<pro; i++){
            System.out.print("\t"+pro_name[i]+"\t\t\t\t");
            for(int j=0; j<res; j++){
                System.out.print(allocation[i][j]+" ");
            }
            System.out.print("\t\t");
            for(int j=0; j<res; j++){
                System.out.print(max[i][j]+" ");
            }
            System.out.print("\t\t");
            for(int j=0; j<res; j++){
                System.out.print(need[i][j]+" ");
            }
            System.out.println();
        }
    }


    void safe_sequence(){
        boolean[] finish = new boolean[pro];
        int[] safe_sequence = new int[pro];
        int count = 0;
        while(count < pro){
            boolean found = false;
            for(int i=0; i<pro; i++){
                if(!finish[i]){
                    int j;
                    for(j=0; j<res; j++){
                        if(need[i][j] > avail_res[j]){
                            break;
                        }
                    }
                    if(j == res){
                        for(j=0; j<res; j++){
                            avail_res[j] += allocation[i][j];
                        }
                        safe_sequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if(!found){
                System.out.println("System is not in safe state");
                return;
            }
        }

        if(count == pro){
            System.out.println("System is in safe state");
            System.out.print("Safe sequence is: ");
            for(int i=0; i<pro; i++){
                System.out.print(pro_name[safe_sequence[i]]+" ");
            }
        }
    }
}

public class Bankers_algorithm {
    public static void main(String[] args){
        Getting_input gi = new Getting_input();
        gi.get_resource();
        gi.get_process();
        gi.get_allocation();
        gi.get_max_allocation();
        gi.calculate_need_matrix();
        gi.display();
        gi.safe_sequence();
    }

}
