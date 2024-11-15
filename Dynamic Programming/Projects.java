import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Job {
    public int startTime;
    public int endTime;
    public int profit;
    
    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
    
    @Override
    public String toString() {
        return "Job{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", profit=" + profit +
                '}';
    }
}

class Solution {
    private int latestJobWithNoConflict(List<Job> jobs, int targetJobIndex) {
        int left = 0, right = jobs.size() - 1;
        int maxIndex = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(jobs.get(mid).endTime < jobs.get(targetJobIndex).startTime) {
                maxIndex = Math.max(maxIndex, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return maxIndex;
    }
    
    private int solve(List<Job> jobs) {
        int n = jobs.size();
        int[] dp = new int[n];
        dp[0] = jobs.get(0).profit;
        for(int i = 1; i < n; i++) {
            int profit = jobs.get(i).profit;
            int latestJobWithNoConflict = latestJobWithNoConflict(jobs, i);
            if(latestJobWithNoConflict != -1) {
                profit += dp[latestJobWithNoConflict];
            }
            dp[i] = Math.max(dp[i-1], profit);
        }
        return dp[n-1];
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        
        List<Job> jobs = new ArrayList<Job>();
        
        for(int i = 0; i < n; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        
        // System.out.println("--------------------------");
        // for(int i = 0; i < n; i++) {
        //     System.out.println(jobs.get(i));
        // }
        // System.out.println("--------------------------");
        
        Collections.sort(jobs, new Comparator<Job>(){
            @Override
            public int compare(Job j1, Job j2) {
                if(j1.endTime < j2.endTime) {
                    return -1;
                } else if(j1.endTime == j2.endTime) {
                    return 0;
                }
                return 1;
            }
        });
        
        return solve(jobs);
    } 
}

/**
 * Projects
 */
public class Projects {
    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            startTime[i] = kb.nextInt();
            endTime[i] = kb.nextInt();
            profit[i] = kb.nextInt();
        }
        System.out.println(new Solution().jobScheduling(startTime, endTime, profit));
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int k = 1;
        //k = kb.nextInt();
        while (k-- > 0) {
            solve(kb);
        }
        kb.close();
    }
}