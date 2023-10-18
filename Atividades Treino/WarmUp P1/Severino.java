import java.util.*;

public class Severino 
{
    public static void main(String[] args)
    {
        Scanner scanf = new Scanner(System.in);

        while(scanf.hasNext())
        {
            int num = scanf.nextInt();
            scanf.nextLine();

            String[] ordenada = new String[num];

            for(int i = 0; i < num; i++)
            {
                String temp = scanf.nextLine();

                int j = i - 1;

                while(j >= 0 && ordenada[j].compareTo(temp) > 0)
                {
                    ordenada[j + 1] = ordenada[j];
                    j--;
                }

                ordenada[j + 1] = temp;
            }

            for(int i = 0; i < num; i++)
            {
                System.out.println(ordenada[i]);
            }
            
        }   
        
        scanf.close();
    }
}
