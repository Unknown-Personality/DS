import java.util.Scanner;

public class Berkeley{

    static String toHours(int min){
        int hrs=min/60;
        min=min%60;

        return hrs+":"+min;
    }

    static int toMinute(String str){
        String[] time=str.split(":");
        int hr=Integer.parseInt(time[0])*60;
        int min=Integer.parseInt(time[1]);

        return hr+min;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Number of Machines : ");
        int n=sc.nextInt();
        sc.nextLine();
        if(n<=0){return;}

        int[] time=new int[n];
        System.out.print("Enter Time for Server (HH:MM): ");
        time[0]=Berkeley.toMinute(sc.nextLine());
        System.out.println();

        for(int i=1;i<n;i++){
            System.out.print("Enter Time for "+(i)+"th Client"+" (HH:MM):");
            time[i]=Berkeley.toMinute(sc.nextLine());
        }

        int avgt=0;

        for(int i=1;i<n;i++){
            avgt+=(time[i]-time[0]);
        }
        avgt=avgt/n;

        System.out.println("Avg Time: "+avgt);
        
        int serverTime=time[0]+avgt;

        System.out.println(toHours(time[0]+avgt));
    }
}