import java.util.Scanner;

public class MaxSubArray
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int[] array = new int[size];

		for(int i = 0; i < array.length; i++)
			array[i] = in.nextInt();
		System.out.println(maxSubArray(array));

	}

	public static int maxSubArray(int[] array)
	{
		int max_so_far = 0, max_ending_here = 0;

		for(int i = 0; i < array.length; i++)
		{
			max_ending_here += array[i];

			if(max_ending_here < 0)
				max_ending_here = 0;
			if(max_so_far < max_ending_here)
				max_so_far = max_ending_here;
		}
		return max_so_far;
	}
}