public class IsRec {

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
    
    public static boolean verificarVogal(String str, int tamanho, boolean vogal, int i)
    {
        if(i < tamanho)//Se esta condição for verdadeira, o código dentro do bloco if será executado
        {
            if(!(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U'))//verifica se não é vogal
            {
                i = tamanho;   //caso não seja I = tamanho, para não entrar na função na proxima chamda
                vogal = false; //e a vogal é mudada para false
            }
            else
            {
                i++;//caso o loop não seja interrompido por algum numero ou consoante, adiciona 1 a i para verificar o proximo valor
            }
            
            vogal = verificarVogal(str, tamanho, vogal, i);//se usa ++i e n i++, porém adicionei ali em cima para nao somar mesmo quando for false
        }

        return vogal;//retorna caso a função não tenha entrado em loop
    }


    //compara individualmente cada char da str com A a Z, e se alguma letra der diferente ele define o codigo para nao entrar em loop novamente e retorna falso
    //caso o char posição esteja entre A e Z, verifica se ele é A,E,I,O ou U, e caso seja,  define o codigo para nao entrar em loop novamente e retorna falso
    public static boolean verificarConso(String str, int tamanho, boolean conso, int i)
    {
        if(i < tamanho)//Se esta condição for verdadeira, o código dentro do bloco if será executado
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

            conso = verificarConso(str, tamanho, conso, ++i);

        }

        return conso;
    }

    //verifica se a string é formada somente por algarismos númericos, caso não seja, define o codigo para nao entrar em loop novamente e retorna falso
    public static boolean verificarInt(String str, int tamanho, boolean inteiro, int i)
    {
        if(i < tamanho)//Se esta condição for verdadeira, o código dentro do bloco if será executado
        {
            if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9'))
            {
                inteiro = false;
                i = tamanho;
            }
            else
            {
                i++;
            }    

            inteiro = verificarInt(str, tamanho, inteiro, i);
        }

        return inteiro;
    }

    //verifica se a string é formada somente por algarismos numericos OU '.' ou ',', pois um inteiro é um float, mas um float não é um inteiro
    //caso seja formado por algo sem ser os citados acima, define o codigo para nao entrar em loop novamente e retorna falso
    //caso seja formado por eles, a cada . ou , adiciona 1 ao contador, e caso o contador seja maior que 1 ele define o codigo para nao entrar em loop novamente e retorna falso, pois um float só é dividido por um . ou uma ,
    public static boolean verificarReal(String str, int tamanho, boolean real, int contador, int i)
    {
        if(i < tamanho)//Se esta condição for verdadeira, o código dentro do bloco if será executado
        {
            if((str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '.' || str.charAt(i) == ',')) //verifica todos os elementos de um float
            {
                if(str.charAt(i) == '.' || str.charAt(i) == ',')
                {
                    contador++;// adiciona ao contador cada . ou , que é contado no codigo
                }

            }
            else
            {
                real = false;
                i = tamanho;
            }

            real = verificarReal(str, tamanho, real, contador, ++i);
        }

        if(contador > 1) // se o contador for > 1 define como falso
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

        verdade = verificarVogal(str, tamanho, true, 0);
        printar(verdade); MyIO.print(" "); //esse MyIO.print(" "); é para o verde ler da maneira correta (que o verde corrige)
        
        verdade = verificarConso(str, tamanho, true, 0);
        printar(verdade); MyIO.print(" ");

        verdade = verificarInt(str, tamanho, true, 0);
        printar(verdade); MyIO.print(" ");

        verdade = verificarReal(str, tamanho, true, 0, 0);
        printar(verdade); MyIO.print("\r\n");//quebra a linha para ficar mais bonito no terminal do pc, nao afeta o verde
        
    }

    public static void codigoRec(Boolean stop)
    {
        if(!stop)
        {
            String str = MyIO.readLine();//lê a string
            int tamanho = str.length(); //define o tamanho da string
            stop = stop(str, tamanho); //define a flag por meio da função, enviando os parametros de str e tamanho
            if(!stop)
            {
                str = str.toUpperCase();//aqui eu usei a função toupper, mas somente depois da flag definir se o codigo rodaria ou não, pois 'fim' é diferente de 'FIM'
                /* O uso do toupper, simplismente reduz o número de buscas que o algoritmo faz, uma vez que 'A' e 'a' continuam sendo vogais e 'B' e 'b' consoantes, então nao tenho que adicionar na busca do codigo 
                *  (>= a && <=z) || (>= A && <= Z), diminuindo as buscas pela metade o numero de comparações, também, quando for verificar as vogais para diferenciar de consoantes, faço 5 comparações ao inves de 10
                */ 
                verificarGeral(str,tamanho); //envia str e tamanho para a função 
            }
            codigoRec(stop);
        }
        return;
    }
    public static void main(String[] args)
    {
        boolean stop = false;

        codigoRec(stop);
    }
}
