import java.util.*;

public class CPU_Scheduling{
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("1. FCFS\n2. SJF\n3. Priority\n4. Round Robin");
        System.out.print("Select your operation (type 1/2/3/4): ");
        int op = s.nextInt();

        //To get the no of process
        int no;
        System.out.print("How many process you want to run : ");
        no = s.nextInt();


        //Declaring all array
        int[] wt = new int[no];
        int[] tat = new int[no];

        //To get the name of all the Process
        System.out.println("Enter the Process name ");
        String[] name = new String[no];
        for (int i=0; i<no; i++){
            name[i] = s.next();
        }

        //To get the burst time for all process
        System.out.println("Enter the Burst time ");
        int[] bt = new int[no];
        for(int i=0; i<no; i++){
            System.out.print(name[i]+". ");
            bt[i] = s.nextInt();
        }

        //To get the Arrival time for all process
        System.out.println("Enter the arrival time ");
        int[] at = new int[no];
        for (int i=0; i<no; i++){
            System.out.print(name[i]+". ");
            at[i] = s.nextInt();
        }

        switch (op){
            case 1:
                arrival_time(no, at, bt, name);

                break;

            case 2:
                //calculation Waiting time wt for SJF
                for(int i=0; i<no; i++){
                    for (int j=i+1; j<no; j++){
                        if(bt[i] > bt[j]){
                            int temp = at[i];
                            at[i] = at[j];
                            at[j] = temp;

                            temp = bt[i];
                            bt[i] = bt[j];
                            bt[j] = temp;

                            String temp1 = name[i];
                            name[i] = name[j];
                            name[j] = temp1;
                        }
                    }
                }
                break;

            case 3:
                //calculation Waiting time wt for Priority
                int[] priority = new int[no];
                System.out.println("Enter the priority ");
                for (int i=0; i<no; i++){
                    System.out.print(name[i]+". ");
                    priority[i] = s.nextInt();
                }
                for(int i=0; i<no; i++){
                    for (int j=i+1; j<no; j++){
                        if(priority[i] > priority[j]){
                            int temp = at[i];
                            at[i] = at[j];
                            at[j] = temp;

                            temp = bt[i];
                            bt[i] = bt[j];
                            bt[j] = temp;

                            String temp1 = name[i];
                            name[i] = name[j];
                            name[j] = temp1;

                            temp = priority[i];
                            priority[i] = priority[j];
                            priority[j] = temp;
                        }
                    }
                }
                break;

            case 4:
                //calculation Waiting time wt for Round Robin
                arrival_time(no, at, bt, name);
                System.out.print("Enter the Quantum time : ");
                int quantum = s.nextInt();

                int[] rem_bt = new int[no];
                for(int i=0; i<no; i++){
                    rem_bt[i] = bt[i];
                }


                int t = 0;
                while(true){
                    boolean done = true;
                    for(int i=0; i<no; i++){
                        if(rem_bt[i] > 0){
                            done = false;
                            if(rem_bt[i] > quantum){
                                t += quantum;
                                rem_bt[i] -= quantum;
                            }else{
                                t += rem_bt[i];
                                wt[i] = t - rem_bt[i];
                                tat[i] = t;
                                rem_bt[i] = 0;
                            }
                        }
                    }
                    if(done){
                        break;
                    }
                }
                break;

            default:
                System.out.println("Please choose correct Operation!!");
        }

        if(op != 4) {
            //For calculating the Waiting time
            wt[0] = 0;
            for (int i = 1; i < no; i++) {
                wt[i] = bt[i - 1] + wt[i - 1];
            }


            //calculating the Turn around time for FCFS
            for(int i=0; i<no; i++){
                tat[i] = bt[i] + wt[i];
            }
        }


        float twt = 0.0f;
        float ttat = 0.0f;

        System.out.println(" Process Name\t\t\t Burst Time \t\t\t Arrival Time\t\t\t Waiting Time \t\t\t Turn Around Time");
        for(int i=0; i<no; i++){
            System.out.println("\t\t"+name[i]+"\t\t\t\t\t\t"+bt[i]+"\t\t\t\t\t\t"+at[i]+"\t\t\t\t\t"+wt[i]+"\t\t\t\t\t\t\t"+tat[i]);
            twt += wt[i];
            ttat += tat[i];
        }

        System.out.println("Average Waiting time : "+twt/no);
        System.out.println("Average Turn around time : "+ttat/no);

    }

    private static void arrival_time(int no, int[] at, int[] bt, String[] name) {
        //calculation Waiting time wt for FCFS
        for(int i = 0; i< no; i++){
            for (int j = i+1; j< no; j++){
                if(at[i] > at[j]){

                    int temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    String temp1 = name[i];
                    name[i] = name[j];
                    name[j] = temp1;
                }
            }
        }
    }
}
