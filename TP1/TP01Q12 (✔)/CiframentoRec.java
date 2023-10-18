public class CiframentoRec 
{

    public static boolean stop(String str, int tamanho)
    {
        boolean stop = false;

        if(tamanho == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M')
        {
            stop = true;
        }

        return stop;
    }

    //Transforma cada letra recebida em seu numero respectivo e soma 3 numeros na posição, depois retorna a letra cifrada
    public static char NovaLetra(char letra)
    {
        int numLetra = (int) letra;

        numLetra = numLetra + 3;

        letra = (char) numLetra;

        return letra;
    }

    public static String cifrar(String str, int tamanho, int i, String novaFrase)
    {
        if(i < tamanho)
        {
            char letra = str.charAt(i); // define a letra de acordo com a posição
                    
            char letraCifrada = NovaLetra(letra); // puxa a função de ciframento

            novaFrase += letraCifrada; // //adiciona cada letra cifrada a nova frase
            
            novaFrase = cifrar(str, tamanho, ++i, novaFrase);//puxa a func novamente
        }

        return novaFrase;
    }


    // lê a entrada do usuário usando o método MyIO.readLine(), verifica se a entrada é igual a “FIM” usando o método stop, e se não for, chama o método cifrar para criptografar a string de entrada, deslocando cada caractere por 3 posições no alfabeto. A string criptografada é então impressa usando o método MyIO.println()
    public static void codigoRec(Boolean stop)
    {
        if(!stop)
        {
            String str = MyIO.readLine();
            int tamanho = str.length();
            stop = stop(str, tamanho);
            if(!stop)
            {
                String novaFrase = "";
                String cifrado = cifrar(str, tamanho, 0, novaFrase);
                MyIO.println(cifrado);
                codigoRec(stop);
            }
            
        }
    }

    public static void main(String[] args)
    {
        boolean stop = false;//predefine condição de parada com falsa
        codigoRec(stop); //chama o metodo a desenvolver o codigo recursivo
    }
}
