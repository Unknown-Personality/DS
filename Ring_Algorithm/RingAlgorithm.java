import java.util.*;
public class RingAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes in ring: ");
        int n = sc.nextInt();

        int[] processes = new int[n];
        boolean[] active = new boolean[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
            active[i] = true;
        }

        System.out.print("Enter failed coordinator process ID: ");
        int failedID = sc.nextInt();

        for (int i = 0; i < n; i++) {
            if (processes[i] == failedID) {
                active[i] = false;
                break;
            }
        }

        System.out.print("Enter process ID that starts election: ");
        int initiatorID = sc.nextInt();

        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (processes[i] == initiatorID) {
                idx = i;
                break;
            }
        }

        System.out.println("\nElection initiated by process " + initiatorID);
        Set<Integer> electionSet = new LinkedHashSet<>();
        int start = idx;

        do {
            if (active[start]) {
                System.out.println("Process " + processes[start] + " passes election message.");
                electionSet.add(processes[start]);
                System.out.println(electionSet);
            }
            start = (start + 1) % n;
        } while (start != idx);

        int newCoordinator = Collections.max(electionSet);
        System.out.println("\nNew Coordinator is: " + newCoordinator);

        sc.close();
    }
}