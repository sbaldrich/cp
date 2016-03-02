import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main{

  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {-1, 0, 1, 0};
  static int R, C;

  static boolean ok(int r, int c){
    return r >= 0 && c >= 0 && r <= R + 1  && c <= C + 1;
  }

  public static int dec(char hex){
   if(hex >= '0' && hex <= '9') return hex - '0';
   return 10 + hex - 'A';
  }

  static int encode(int row, int col){
    return row * 10000 + col;
  }

  static int row(int val){
    return val / 10000;
  }

  static int col(int val){
    return val % 10000;
  }

  static void bfs(char[][] stencil){
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);
    while(!queue.isEmpty()){
      int top = queue.poll().intValue();
      int r = row(top), c = col(top);
      stencil[r][c] = '.';
      for(int k = 0; k < 4; k++){
        if(ok(r + dr[k], c + dc[k]) && stencil[r + dr[k]][c + dc[k]] == ' '){
          queue.offer(encode(r + dr[k], c + dc[k]));
          stencil[r + dr[k]][c + dc[k]] = '.';
        }
      }
    }
  }

  public static void main(String[] args) throws Exception{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line, pars[];
    int H, W;
    while((line = reader.readLine()) != null){
      pars = line.split("\\s+");
      H = Integer.valueOf(pars[0]);
      W = Integer.valueOf(pars[1]);
      R = H;
      C = 4 * W;
      if(H == 0 && W == 0)
      break;
      char[][] stencil = new char[2 + H][2 + 4 * W];
      for(int c = 0; c <= C + 1; c++)
        stencil[0][c] = stencil[H+1][c] = ' ';
      for(int h = 1; h <= H; h++){
        stencil[h][0] = stencil[h][C + 1] = ' ';
        for(int w = 0, x; w < W; w++){
          x = dec((char)reader.read());
          for(int k = 0; k < 4; k++)
            stencil[h][1 + (w * 4 + 3 - k)] = (x>>k & 1) == 1 ? 'x': ' ';
        }
        reader.skip(1L);
      }
      bfs(stencil);
      for(int h = 0; h <= H + 1; h++){
        for(int k = 0; k <= C + 1; k++)
            System.out.print(stencil[h][k]);
        System.out.println();
      }
    }
  }


}
