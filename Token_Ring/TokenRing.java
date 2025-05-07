import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes in ring: ");
        int n = sc.nextInt();

        boolean[] hasToken = new boolean[n];
        int tokenHolder = 0; // Initially, process 0 has the token
        hasToken[tokenHolder] = true;

        System.out.println("Initial token holder is process " + tokenHolder);

        char choice;
        do {
            System.out.print("\nEnter process number to request critical section: ");
            int request = sc.nextInt();

            if (hasToken[request]) {
                System.out.println("Process " + request + " enters critical section.");
            } else {
                System.out.println("Token passed from process " + tokenHolder + " to process " + request);
                hasToken[tokenHolder] = false;
                tokenHolder = request;
                hasToken[tokenHolder] = true;
                System.out.println("Process " + request + " enters critical section.");
                try{Thread.sleep(2000);}
                catch(InterruptedException e){}
                System.out.println("Process " + request + " leaves the critical section.");
                tokenHolder=(tokenHolder+1)%n;
                hasToken[tokenHolder] = true;
                System.out.println("Token passed to process " + tokenHolder );
            }

            System.out.print("Do you want to continue? (y/n): ");
            choice = sc.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');

        sc.close();
    }
}