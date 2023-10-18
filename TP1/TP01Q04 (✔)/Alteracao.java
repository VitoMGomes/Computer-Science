import java.util.Random;

public class Alteracao 
{
    /*Define a flag stop como verdadeira ou falsa */
   public static boolean stop(String string)
   {
        return string.length() == 3 && string.charAt(0) == 'F' && string.charAt(1) == 'I' && string.charAt(2) == 'M';
   }

   //verifica o tamanho da string e retorna ele
   public static int tamanho(String string)
   {
        return string.length();
   }

   /*Recebe a função random, a string e o tamanho da string */

   public static void alterar(String frase, int tamanho, Random random)
   {
        char letra1 = (char)('a' + (Math.abs(random.nextInt()) % 26)); // Gera a letra a ser substituida
        char letra2 = (char)('a' + (Math.abs(random.nextInt()) % 26)); // Gera a letra a substituir

        String string2 ="";
        /* Após a criação da string2, que armazenará, será verificada cada posição da string
         * Caso ela seja a letra a ser substituida,a nova será adicionada a posição
         * e caso não seja, ela sera mantina na mesma posição na nova string
         */
        for(int i = 0; i < tamanho; i++)
        {
            if(frase.charAt(i) == letra1)
            {
                string2 += letra2;
            }
            else
            {
                string2 += frase.charAt(i);
            }
        }

        MyIO.println(string2);//printa o resultado

        return;
    }
    public static void main(String[] args)
    {
        boolean stop;//ja define a variavel que sera a flag (FIM)
        Random random = new Random();
        random.setSeed(4);
        String frase;

        do
        {
            frase = MyIO.readLine(); //lê a string a ser alterada
            stop = stop(frase);//chama afunção que define a flag
            int tamanho = tamanho(frase);//chama a função que define tamanho

            if(!stop) //entra caso diferent da flag
            {
                alterar(frase, tamanho, random);//chama função que irá definir a letra a ser trocada e por qual sera trocada, e printará a resposta nela mesma
            }

        }while(!stop);// não inicia a repetição novamente quando recebe a flag "FIM"
        
    }  
    
}
