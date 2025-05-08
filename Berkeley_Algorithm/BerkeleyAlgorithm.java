import java.util.*;

public class BerkeleyAlgorithm {
    // Converts HH:MM to total minutes
    static int toMinutes(String time) {
        String[] parts = time.split(":");
        int hr = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        return hr * 60 + min;
    }

    // Converts total minutes to HH:MM
    static int toHH(int minutes) {
        int hr = minutes / 60;
        
        return hr;
    }

    static String toMM(int minutes) {
        int min = minutes % 60;

        if(min==0){
            return "00"; 
        }
        return Integer.toString(min);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clocks (including master): ");
        int n = sc.nextInt();
        sc.nextLine();

        int[] times = new int[n];
        System.out.print("Enter time of master " + " (HH:MM): ");
        String timeStr = sc.nextLine();
        times[0] = toMinutes(timeStr);

        for (int i = 1; i < n; i++) {
            System.out.print("Enter time of slave " + (i) + " (HH:MM): ");
            timeStr = sc.nextLine();
            times[i] = toMinutes(timeStr);
        }

        int masterTime = times[0];
        int sumDiff = 0;

        System.out.println("\nTime differences w.r.t. master:");
        for (int i = 0; i < n; i++) {
            int diff = times[i] - masterTime;
            sumDiff += diff;
            System.out.println("Clock " + (i + 1) + ": " + diff + " minutes");
        }

        int avgDiff = sumDiff / n;

        System.out.println("\nAverage difference: " + avgDiff + " minutes");

        System.out.println("\nSynchronized Clocks:");
        for (int i = 0; i < n; i++) {
            int newTime =( masterTime )+ avgDiff;
            System.out.println("Clock " + (i + 1)+":" + toHH(newTime)+":"+toMM(newTime));
        }

        sc.close();
    }
}