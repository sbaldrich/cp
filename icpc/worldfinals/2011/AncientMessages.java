import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main{

  public static int dec(char hex){
   if(hex >= '0' && hex <= '9') return hex - '0';
   return 10 + hex - 'A';
  }

  public static void main(String[] args) throws Exception{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line, pars[];
    int H, W;
    while((line = reader.readLine()) != null){
      pars = line.split("\\s+");
      H = Integer.valueOf(pars[0]);
      W = Integer.valueOf(pars[1]);
      if(H == 0 && W == 0)
      break;
      short[][] stencil = new short[2 + H][2 + 4 * W];
      for(int h = 1; h <= H; h++){
        for(int w = 0, x; w < W; w++){
          x = dec((char)reader.read());
          for(int k = 0; k < 4; k++)
            stencil[h][1 + (w * 4 + 3 - k)] = (short)(x>>k & 1);
        }
        reader.skip(1L);
      }
      for(int h = 0; h <= H + 1; h++)
        System.out.println(Arrays.toString(stencil[h]));
    }
  }
}
