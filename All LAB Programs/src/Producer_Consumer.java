import java.util.Scanner;
public class Producer_Consumer {

    static int mutex = 1, empty = 0, full = 0, x = 0;
    static int wait(int s){
        return --s;
    }
    static int signal(int s){
        return ++s;
    }

    static void producer(){
        mutex = wait(mutex);
        full = signal(full);
        empty = wait(empty);
        x++;
        System.out.println("Producer produced an item : "+x);
        mutex = signal(mutex);
    }

    static void consumer(){
        mutex = wait(mutex);
        full = wait(full);
        empty = signal(empty);
        System.out.println("Consumer consumed an item : "+x);
        x--;
        mutex = signal(mutex);
    }

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.print("Enter your buffer size :");
        empty = s.nextInt();
        System.out.println("1. Producer\n2. Consumer\n3. Exit");
        while(true){
            System.out.print("Enter your choice : ");
            int choice = s.nextInt();

            switch(choice){
                case 1:
                    if((mutex==1) && (empty != 0)){
                        producer();
                    }
                    else{
                        System.out.println("Buffer size is full!! No space!!");
                    }
                    break;
                case 2:
                    if((mutex ==1) && (full != 0)){
                        consumer();
                    }
                    else{
                        System.out.println("Buffer is empty!! No item is currently present!!");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Proper choice is not given!!");
            }
        }
    }
}
