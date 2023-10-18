import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {
    
    public static void main(String[] args)
    {
        try {
            int quantidade = MyIO.readInt(); //quantidade de numeros a serem inseridos no arquivo
            RandomAccessFile arq = new RandomAccessFile("texto.txt", "rw"); // cria um metodo para ler e escrever dados no arquivo teste.txt (rw = read and write)


            for(int i = 0; i < quantidade; i++) //loop com a quantidade de numeros a serem inseridos
            {
                double num = MyIO.readDouble(); //le os reais a serem inseridos
                arq.writeDouble(num); //insere no arquivo
            }

            arq.close();// fecha o arquivo

            arq = new RandomAccessFile("texto.txt", "r");// cria metodo para ler o arquivo

            for(int i = quantidade - 1; i >= 0 ; i--) // define o loop da quantidade de tras pra frente
            {
                arq.seek(i * 8);// busca o byte de cada numero double, seguindo o for de tras pra frente
                double resultD = arq.readDouble();// passa o valor lido do arquivo para a variavel
                
                int resultI = (int) resultD ; //usando TypeCasting transforma em int o resultD

                if(resultI == resultD) // se for igual, imprime o valor inteiro, se não, o double
                {
                    MyIO.println(resultI);
                }
                else
                {
                    MyIO.println(resultD);
                }
            }

            arq.close();//fecha o arquivo
        } catch (IOException e) {}
    }
}
