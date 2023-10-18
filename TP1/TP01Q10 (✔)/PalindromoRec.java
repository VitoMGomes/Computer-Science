public class PalindromoRec {

    /*
     * verifica se a string é FIM para encerrar o programa
     */
    public static boolean stop(String str, int tamanho) {
        boolean stop = true;

        if (!(tamanho == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M')) {
            stop = false;
        }

        return stop;
    }


    /*
     * o metodo verifica se i é menor que tamanho, caso sim entra no condicional, depois verifica se é palindromo,
     * caso não for, iguala i com tamanho e seta a variavel como false, fazendo o loop nao repetir depois da proxima recursao e retornar
     * falso
     */
    public static boolean isPal(String str, int tamanho, int i, boolean pal)
    {
        if(i < tamanho)
        {
            if(!(str.charAt(i) == str.charAt(tamanho)))
            {
                pal = false;
                i = tamanho;
            }

            pal = isPal(str, --tamanho, ++i, pal);
        }

        return pal;
    }

    public static void codigoRec(boolean stop)
    {
        if (!stop) {
            String str = MyIO.readLine();
            int tamanho = str.length();
            stop = stop(str, tamanho);
            if(!stop)//só entra se a str for diferente de FIM
            {
                Boolean pal = isPal(str, tamanho - 1, 0, true); //puxa a função pra verificar pal
                if(pal) MyIO.println("SIM");
                else { MyIO.println("NAO");}
                codigoRec(stop);//puxa a função codigoRec novamente
            }
        }

        return;
    }

    public static void main(String[] args) {
        boolean stop = false;
        codigoRec(stop);//chama o metodo recursivo
    }

}