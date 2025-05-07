import java.util.Scanner;

public class Token_Ring {

    static class Process {
        int id;
        boolean hasToken = false;

        public Process(int id) {
            this.id = id;
        }

        public void receiveToken() {
            hasToken = true;
        }

        public void executeCriticalSection() {
            if (hasToken) {
                System.out.println("Process " + id + " is in critical section.");
                // simulate work
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                System.out.println("Process " + id + " is leaving critical section.");
                hasToken = false;
            } else {
                System.out.println("Process " + id + " cannot enter critical section. No token.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes in ring: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }

        // Give token to the first process
        processes[0].receiveToken();

        int tokenHolder = 0;

        while (true) {
            System.out.println("\nCurrent token holder: Process " + tokenHolder);
            System.out.print("Do you want Process " + tokenHolder + " to enter CS? (yes/no/exit): ");
            String input = sc.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Terminating token ring.");
                break;
            }

            if (input.equalsIgnoreCase("yes")) {
                processes[tokenHolder].executeCriticalSection();
            }

            // Pass token to next process
            tokenHolder = (tokenHolder + 1) % n;
            processes[tokenHolder].receiveToken();
        }

        sc.close();
    }
}
