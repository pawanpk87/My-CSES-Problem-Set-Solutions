import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Apartments {
    public static int solve(int k, ArrayList<Integer> desiredSize, ArrayList<Integer> apartments){
        int n = desiredSize.size();
        int m = apartments.size();

        int count = 0;
        int i = 0, j = 0;

        Collections.sort(desiredSize);
        Collections.sort(apartments);

        while(i < n && j < m){
            int currDesiredSize = desiredSize.get(i);
            int currApartmentSize = apartments.get(j);

            if(currDesiredSize < (currApartmentSize - k)){
                i++;
            }
            else if(currDesiredSize > (currApartmentSize + k)) {
                j++;
            }else{
                count++;
                i++; j++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        int n, m, k;

        n = kb.nextInt();
        m = kb.nextInt();
        k = kb.nextInt();

        ArrayList<Integer> desiredSize = new ArrayList<>(n);
        ArrayList<Integer> apartments = new ArrayList<>(m);

        int num = 0;

        for(int i = 0; i < n; i++){
            num = kb.nextInt();
            desiredSize.add(num);
        }

        for(int i = 0; i < m; i++){
            num = kb.nextInt();
            apartments.add(num);
        }

        System.out.println(solve(k, desiredSize, apartments));
    }
}
