public class Is
{
    /*Recebe o resultado de alguma das funções e printa SIM ou NAO dependendo do valor da bool
     */
    public static void printar(boolean printar)
    {
        if(printar)
        {
            MyIO.print("SIM");
        }
        else
        {
            MyIO.print("NAO");
        }
    
    }
    
    // Compara individulamente cada char da string com A,E,I,O ou U, e caso algum seja diferente ele encerra o loop e muda a bool a ser retornada para false
    public static boolean verificarVogal(String str, int tamanho)
    {
        boolean vogal = true;

        for(int i = 0; i < tamanho; i++)
        {
            if(!(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U'))
            {
                i = tamanho;
                vogal = false;
            }
        }

        return vogal;
    }

    //compara individualmente cada char da str com A a Z, e se alguma letra der diferente ele instantaneamente encerra o loop e retorna falso.
    //caso o char posição esteja entre A e Z, verifica se ele é A,E,I,O ou U, e caso seja, instantaneamente encerra o loop e retorna falso
    public static boolean verificarConso(String str, int tamanho)
    {
        boolean conso = true;

        for(int i = 0; i< tamanho; i++)
        {
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
            {
                if(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U')
                {
                    i = tamanho;
                    conso = false;
                }
            }
            else
            {
                i = tamanho;
                conso = false;
            }
        }

        return conso;
    }

    //verifica se a string é formada somente por algarismos númericos
    public static boolean verificarInt(String str, int tamanho)
    {
        boolean inteiro = true;

        for(int i = 0; i < tamanho; i++)
        {
            if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9'))
            {
                inteiro = false;
                i = tamanho;
            }
        }

        return inteiro;
    }

    //verifica se a string é formada somente por algarismos numericos OU '.' ou ',', pois um inteiro é um float, mas um float não é um inteiro
    //caso seja formado por algo sem ser os citados acima, encerra o codigo imediatamente e retorna falso
    //caso seja formado por eles, a cada . ou , adiciona 1 ao contador, e caso o contador seja maior que 1 ele encerra o codigo e retorna falso, pois um float só é dividido por um . ou uma ,
    public static boolean verificarReal(String str, int tamanho)
    {
        boolean real = true;
        int contador = 0;

        for(int i = 0; i < tamanho; i++)
        {
            if((str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '.' || str.charAt(i) == ','))
            {
                if(str.charAt(i) == '.' || str.charAt(i) == ',')
                {
                    contador++;
                }
            }
            else
            {
                real = false;
                i = tamanho;
            }
        }
        if(contador > 1)
        {
            real = false;
        }



        return real;
    }
    
    //Compara a string com 'FIM', e caso isso ocorra retorna true, para STOP
    //o codigo da main roda enquanto !STOP, pois quando der verdadeiro sera interrompido
    public static boolean stop(String str, int tamanho)
    {
        boolean stop = false;
        if (tamanho == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') 
        {
        stop = true;
        }

        return stop;
    }

    //aqui a função enviará a str e o tamanho para cada função verificadora, e depois
    //enviará o resultado de cada uma delas para a função printar
    public static void verificarGeral(String str, int tamanho)
    {
        boolean verdade;

        verdade = verificarVogal(str, tamanho);
        printar(verdade); MyIO.print(" "); //esse MyIO.print(" "); é para o verde ler da maneira correta
        
        verdade = verificarConso(str, tamanho);
        printar(verdade); MyIO.print(" ");

        verdade = verificarInt(str, tamanho);
        printar(verdade); MyIO.print(" ");

        verdade = verificarReal(str, tamanho);
        printar(verdade); MyIO.print("\r\n");//quebra a linha para ficar mais bonito no terminal do pc, nao afeta o verde
        
    }
    public static void main(String[] args)
    {
        boolean stop; // Predefine as variavies a serem usadas na main 
        String str;  
        int tamanho;

        do
        {
            str = MyIO.readLine(); //lê a string

            tamanho = str.length(); //define o tamanho da string
            stop = stop(str, tamanho); //define a flag por meio da função, enviando os parametros de str e tamanho 

            if(!stop) //caso a flag(FIM) nao seja lida, da continuidade no programa
            {
                str = str.toUpperCase();//aqui eu usei a função toupper, mas somente depois da flag definir se o codigo rodaria ou não, pois 'fim' é diferente de 'FIM'
                /* O uso do toupper, simplismente reduz o número de buscas que o algoritmo faz, uma vez que 'A' e 'a' continuam sendo vogais e 'B' e 'b' consoantes, então nao tenho que adicionar na busca do codigo 
                *  (>= a && <=z) || (>= A && <= Z), diminuindo as buscas pela metade o numero de comparações, também, quando for verificar as vogais para diferenciar de consoantes, faço 5 comparações ao inves de 10
                */ 
                verificarGeral(str,tamanho); //envia str e tamanho para a função          
            }

        }while(!stop);//fim do while caso str = 'FIM'
    }
}