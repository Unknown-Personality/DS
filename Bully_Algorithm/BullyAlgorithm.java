import java.util.*;

public class BullyAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] processes = new int[n];
        boolean[] alive = new boolean[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
            alive[i] = true;
        }

        System.out.print("Enter crashed process ID (coordinator): ");
        int crashedID = sc.nextInt();

        for (int i = 0; i < n; i++) {
            if (processes[i] == crashedID) {
                alive[i] = false;
                break;
            }
        }

        System.out.print("Enter process that detects failure and starts election: ");
        int initiator = sc.nextInt();

        System.out.println("\nElection started by process " + initiator);
        int newCoordinator = -1;

        for (int i = 0; i < n; i++) {
            if (processes[i] > initiator && alive[i]) {
                System.out.println("Process " + initiator + " sends election message to " + processes[i]);
                newCoordinator = Math.max(newCoordinator, processes[i]);
            }
        }

        if (newCoordinator == -1) {
            System.out.println("No higher process alive. " + initiator + " becomes coordinator.");
            newCoordinator = initiator;
        } else {
            System.out.println("Process " + newCoordinator + " becomes the new coordinator.");
        }

        sc.close();
    }
}