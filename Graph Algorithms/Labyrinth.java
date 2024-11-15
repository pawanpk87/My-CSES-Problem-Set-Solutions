import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    public static class  Point {
        public int row;
        public int col;
        public StringBuilder path;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.path = new StringBuilder();
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", path=" + path.toString() +
                    '}';
        }
    }

    public void solve(char[][] grid, int n, int m) {
        Queue<Point> bfs = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 'A') {
                    bfs.add(new Point(i, j));
                }
            }
        }

        String result = null;
        
        while (!bfs.isEmpty()) {
            int childCount = bfs.size();
            while (childCount-- > 0) {
                Point currNode = bfs.poll();

                //System.out.println(currNode);

                int currRow = currNode.row;
                int currCol = currNode.col;

                if(grid[currRow][currCol] == 'B') {
                    result = currNode.path.toString();
                    break;
                }

                grid[currRow][currCol] = '#';
                
                // UP
                if(isValidMove(currRow-1, currCol, n, m) && grid[currRow-1][currCol] != '#') {
                    Point pointU = new Point(currRow-1, currCol);
                    pointU.path = new StringBuilder(currNode.path).append('U');
                    bfs.add(pointU);
                    grid[currRow-1][currCol] = '#';
                }

                // DOWN
                if(isValidMove(currRow+1, currCol, n, m) && grid[currRow+1][currCol] != '#') {
                    Point pointD= new Point(currRow+1, currCol);
                    pointD.path = new StringBuilder(currNode.path).append('D');
                    bfs.add(pointD);
                    grid[currRow+1][currCol] = '#';
                }

                // LEFT
                if(isValidMove(currRow, currCol-1, n, m) && grid[currRow][currCol-1] != '#') {
                    Point pointL= new Point(currRow, currCol-1);
                    pointL.path = new StringBuilder(currNode.path).append('L');
                    bfs.add(pointL);
                    grid[currRow][currCol-1] = '#';
                }

                // RIGHT
                if(isValidMove(currRow, currCol+1, n, m) && grid[currRow][currCol+1] != '#') {
                    Point pointR= new Point(currRow, currCol+1);
                    pointR.path = new StringBuilder(currNode.path).append('R');
                    bfs.add(pointR);
                    grid[currRow][currCol+1] = '#';
                }
            }
            if(result != null) {
                break;
            }
        }

        if(result == null) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(result.length());
            System.out.println(result);
        }
    }

    public boolean isValidMove(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 &&
               i < n && j < m;
    }
}

/**
 * Projects
 */
public class Labyrinth {
    public static void solve(Scanner kb) {
        int n = kb.nextInt();
        int m = kb.nextInt();
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++) {
            String input = kb.next();
            //kb.next();
            for(int j = 0; j < m; j++) {
                grid[i][j] = input.charAt(j);
            }
        }
        new Solution().solve(grid, n, m);
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