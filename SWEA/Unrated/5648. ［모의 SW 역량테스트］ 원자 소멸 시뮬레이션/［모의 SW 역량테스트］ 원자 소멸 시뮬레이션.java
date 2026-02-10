import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

// 5648. [모의 SW 역량테스트] 원자 소멸 시뮬레이션

public class Solution {
    static class Coord {
        int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] arr;
    static List<Atom> atoms;
    static List<Coord> coords;

    static class Atom {
        int x, y, energy, dir;
        boolean isMoved;

        Atom(int x, int y, int dir, int energy) {
            // 배열 위치로 변환하여 저장
            this.x = (x + 1000) * 2;
            this.y = (y + 1000) * 2;
            this.dir = dir;
            this.energy = energy;
            isMoved = false;

        }
    }

    public static Atom findByIndex(int x, int y) {
        for (Atom a : atoms) {
            if (a.x == x && a.y == y)
                return a;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        arr = new int[4001][4001];
        int testCase = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= testCase; t++) {

            int atomsNum = Integer.parseInt(bf.readLine());
            atoms = new ArrayList<>();

            for (int i = 0; i < atomsNum; i++) {

                st = new StringTokenizer(bf.readLine());
                int a[] = new int[4];
                for (int j = 0; j < 4; j++)
                    a[j] = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(a[0], a[1], a[2], a[3]));
                arr[(a[1] + 1000) * 2][(a[0] + 1000) * 2] = 2;
            }

            int result = func();

            System.out.println("#" + t + " " + result);
        }

    }

    public static int func() {

        int result = 0;
        int dx[] = { 0, 0, -1, 1 };
        int dy[] = { 1, -1, 0, 0 };

        // 어레이리스트가 완전히 빌 때 까지
        while (!atoms.isEmpty()) {

            // 배열에 있는 애들을 다 움직이기
            // 만약에 이미 있는 곳으로 움직이려 한다면 해당 위치 좌표 저장하기

            // 그리고 해당 좌표에 있는 것들을 삭제해주기

            // 배열의 원소들을 하나씩 돌아가면서 이동시키기
            coords = new ArrayList<>();
            for (int i = atoms.size() - 1; i >= 0; i--) {

                Atom a = atoms.get(i);

                int togoX = a.x + dx[a.dir];
                int togoY = a.y + dy[a.dir];
                if (togoX < 0 || togoX > 4000 || togoY < 0 || togoY > 4000) {
//                    삭제 전 초기화 필요
                    arr[a.y][a.x] = 0;
                    atoms.remove(i);
                    continue;
                }
                if (arr[togoY][togoX] == 2) {
                    // 해당 위치를 어레이리스트에 넣어주기
                    // 이미 있는 위치라면 컨티뉴...?
                    coords.add(new Coord(togoX, togoY));
                    arr[togoY][togoX] = 3;
                }

                arr[a.y][a.x] = 0;

                a.x = togoX;
                a.y = togoY;

                if (arr[a.y][a.x] == 0)
                    arr[a.y][a.x] = 2;

            }
            // 겹치는 좌표에 있는 것들 지우기
            Iterator<Coord> cd = coords.iterator();
            while (cd.hasNext()) {
                Iterator<Atom> it = atoms.iterator();

                Coord c = cd.next();
                while (it.hasNext()) {
                    Atom a = it.next();
                    if (a.x == c.x && a.y == c.y) {
                        result += a.energy;
                        it.remove();
                    }
                }
                arr[c.y][c.x] = 0;
                cd.remove();
            }
        }

        return result;
    }

}