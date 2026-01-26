import java.util.Scanner;

public class Main {
    static class Pos {
        int dir;
        int dist;
        Pos(int dir, int dist) {
            this.dir = dir;
            this.dist = dist;
        }
    }

    static int W, H, P;

    static boolean isOpposite(int a, int b) {
        return (a == 1 && b == 2) || (a == 2 && b == 1) || (a == 3 && b == 4) || (a == 4 && b == 3);
    }

    static int dist(Pos a, Pos b) {

        if (a.dir == b.dir) return Math.abs(a.dist - b.dist);

        if (isOpposite(a.dir, b.dir)) {
            if ((a.dir == 1 && b.dir == 2) || (a.dir == 2 && b.dir == 1)) {
                int s1 = a.dist + b.dist + H;
                int s2 = (W - a.dist) + (W - b.dist) + H;
                return Math.min(s1, s2);
            } else { // 3-4
                int s1 = a.dist + b.dist + W;
                int s2 = (H - a.dist) + (H - b.dist) + W;
                return Math.min(s1, s2);
            }
        }

        int s = 0;

        // 북(1) - 서(3) 
        if ((a.dir == 1 && b.dir == 3) || (a.dir == 3 && b.dir == 1)) {
            int north = (a.dir == 1) ? a.dist : b.dist;
            int west  = (a.dir == 3) ? a.dist : b.dist;
            s = north + west;
        }
        // 북(1) - 동(4) 
        else if ((a.dir == 1 && b.dir == 4) || (a.dir == 4 && b.dir == 1)) {
            int north = (a.dir == 1) ? a.dist : b.dist;
            int east  = (a.dir == 4) ? a.dist : b.dist;
            s = (W - north) + east;
        }
        // 남(2) - 서(3) 
        else if ((a.dir == 2 && b.dir == 3) || (a.dir == 3 && b.dir == 2)) {
            int south = (a.dir == 2) ? a.dist : b.dist;
            int west  = (a.dir == 3) ? a.dist : b.dist;
            s = south + (H - west);
        }
        // 남(2) - 동(4)
        else if ((a.dir == 2 && b.dir == 4) || (a.dir == 4 && b.dir == 2)) {
            int south = (a.dir == 2) ? a.dist : b.dist;
            int east  = (a.dir == 4) ? a.dist : b.dist;
            s = (W - south) + (H - east);
        }

        return Math.min(s, P - s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        W = sc.nextInt();
        H = sc.nextInt();
        P = 2 * (W + H);

        int n = sc.nextInt();
        Pos[] shops = new Pos[n];

        for (int i = 0; i < n; i++) {
            int dir = sc.nextInt();
            int dist = sc.nextInt();
            shops[i] = new Pos(dir, dist);
        }

        Pos dong = new Pos(sc.nextInt(), sc.nextInt());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += dist(dong, shops[i]);
        }

        System.out.println(sum);
        sc.close();
    }
}
