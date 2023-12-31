package baek;

import java.io.*;
import java.util.*;

public class Pro_14500_테트로미노 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		su = new int[N][M];
		visited = new boolean[N][M];
		dr = new int[] { -1, 1, 0, 0 };
		dc = new int[] { 0, 0, -1, 1 };
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				su[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, su[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}

	static int[] dr;
	static int[] dc;
	static int N;
	static int M;
	static int[][] su;
	static boolean[][] visited;
	static int max;

	public static void dfs(int r, int c, int depth, int sum) {
		if (depth == 4) {//4칸이면 돌아나와라
			max = Math.max(sum, max);//sum최대 값인지 확인
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, depth + 1, sum + su[nr][nc]);
				if (depth + 1 == 3) {//세칸했을때 그 직전 칸으로 돌아와서 다시 dfs하기=> ㅗ모양 찾기 가능!!!!
					dfs(r, c, depth + 1, sum + su[nr][nc]);
				}
				visited[nr][nc] = false;
			}
		}
	}

}









//풀이2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		su = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<int[]>();
		dr = new int[] { -1, 1, 0, 0 };
		dc = new int[] { 0, 0, -1, 1 };
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				su[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, su[i][j]);
				visited[i][j] = false;
				bfs(i, j, new int[] {0,1,2});
				bfs(i, j, new int[] {0,2,3});
				bfs(i, j, new int[] {0,1,3});
				bfs(i, j, new int[] {1,2,3});

			}
		}
		System.out.println(max);
	}

	static int[] dr;
	static int[] dc;
	static int N;
	static int M;
	static int[][] su;
	static boolean[][] visited;
	static int max;
	static Queue<int[]> queue;

	public static void dfs(int r, int c, int depth, int sum) {
		if (depth == 4) {
			max = Math.max(sum, max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, depth + 1, sum + su[nr][nc]);
				visited[nr][nc] = false;
			}
		}
	}

	public static void bfs(int r, int c, int[] arr) {
		int count = 0;
		int sum = su[r][c];
		for (int i = 0; i < 3; i++) {
			int nr = r + dr[arr[i]];
			int nc = c + dc[arr[i]];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				count++;
				sum += su[nr][nc];
			}
		}
		if (count == 3) {
			max = Math.max(sum, max);
		}
	}
}






