import java.util.*;

public class Espelho
{
    public static void main(String [] args)
    {
        Scanner scanf = new Scanner(System.in);

        while(scanf.hasNext())
		{
		
			int min = scanf.nextInt();
			int max = scanf.nextInt();
         	String nums =" ";

			for(int z = min; z <= max; z++)
			{
				System.out.print(z);
				nums += z;
			}
			
			for(int y = nums.length(); y >= 1; y--)
			{
				System.out.print(nums.charAt(y-1));
				
			}

			System.out.println("");
		}

        scanf.close();
    }
}

