import java.util.*;
class Kadane
{
  int[] maxSubarray(int[] a) 
  {
    double max_so_far = 0;
    double max_ending_here = 0;
    int max_start_index = 0;
    int startIndex = 0;
    int max_end_index = -1;

    for(int i = 0; i < a.length; i++) 
    {
      if(0 > max_ending_here +a[i]) 
      {
        startIndex = i+1;
        max_ending_here = 0;
      }
      else 
      {
        max_ending_here += a[i];
      }

      if(max_ending_here > max_so_far) {
        max_so_far = max_ending_here;
        max_start_index = startIndex;
        max_end_index = i;
      }
    }

    if(max_start_index <= max_end_index) {
      return Arrays.copyOfRange(a, max_start_index, max_end_index+1);
    }

    return null;
  }
}

public class sample
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    int test = in.nextInt();
    while(test-- != 0)
    {
    int size = in.nextInt();
    int[] array = new int[size];
    int  noncont = 0;
    for(int i = 0; i < array.length; i++)
    {
      array[i] = in.nextInt();
      if(array[i] > 0)
        noncont += array[i];
    }
    Kadane k = new Kadane();
    int res[] = k.maxSubarray(array);
    if(res != null)
    {
      int cont = 0;
      for(int i = 0; i < res.length; i++)
      {
        cont += res[i];
        
      }
      System.out.println(cont+" "+noncont);
    }
    else
    {
    Arrays.sort(array);
    System.out.println(array[array.length-1]+" "+array[array.length-1]);
  }


  }

  }
}
