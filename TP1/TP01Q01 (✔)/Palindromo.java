import java.util.Scanner;

public class Palindromo
{
    //verifica se a string é a flag FIM
    public static boolean stop(String str, int tamanho)
    {
        boolean stop = true;

        if(!(tamanho == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M'))
        {
            stop = false;
        }

        return stop;
    }
    public static void main(String[] args)
    {
        Scanner scanf = new Scanner(System.in);

        String palavra;

        boolean stop = false;


        while(!(stop))
        {
            boolean palindromo = true;
            palavra = scanf.nextLine();
            int tamanho = palavra.length();
            stop = stop(palavra, tamanho);

            if(!(stop))
            {

                for(int i = 0; i < tamanho/2; i++)
                {
                    if(palavra.charAt(i) != palavra.charAt(tamanho - i - 1))
                    {
                        i = tamanho;
                        palindromo = false;
                    }
                        
                }//fim for

                if(palindromo)
                {
                    System.out.println("SIM");
                }
                else
                {
                    System.out.println("NAO");
                }//fim condicional(2)
            }//fim condicional(1)
        }//fim while


        scanf.close();
    }
}