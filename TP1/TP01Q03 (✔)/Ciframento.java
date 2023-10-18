public class Ciframento
{

    //Transforma cada letra recebida em seu numero respectivo e soma 3 numeros na posição, depois retorna a letra cifrada
    public static char NovaLetra(char letra)
    {
        int numLetra = (int) letra;

        numLetra = numLetra + 3;

        letra = (char) numLetra;

        return letra;
    }

    public static void main(String[] args)
    {

        String frase = ""; // predefine a frase
        boolean stop = frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M'; //(!palavra.equals("FIM"))
        while(!stop) // enquanto a flag FIM não for inserida o programa roda (!palavra.equals("FIM"))
        {
            String novaFrase = "";// aqui serão adicionadas as letras cifradas | zerada a cada repetição
            frase = MyIO.readLine(); //lera a frase
            stop = frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M'; //(!palavra.equals("FIM"))
            if(!stop) //se a frase for diferente da flag, executa o ciframento (!palavra.equals("FIM"))
            {
                int tamanho = frase.length(); // puxa o tamanho da frase

                for(int letraP = 0; letraP < tamanho; letraP++) //entrara com letra por letra
                {
                    char letra = frase.charAt(letraP); // define a letra de acordo com a posição
                    
                    char letraCifrada = NovaLetra(letra); // puxa a função de ciframento

                    novaFrase += letraCifrada; //adiciona cada letra cifrada a nova frase
                }
            
                MyIO.println(novaFrase);
            }
        }
    }
}