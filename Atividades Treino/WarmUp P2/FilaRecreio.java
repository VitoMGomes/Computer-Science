import java.util.*;
import java.io.*;

//java FilaRecreio > saida.out

public class FilaRecreio
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int entradas = scanner.nextInt();

        for(int i = 0; i < entradas; i++)
        {
            int tamanho = scanner.nextInt();
            int[] lista = new int[tamanho];
            int[] listaOrd = new int[tamanho];

            for(int j = 0; j < tamanho; j++)
            {
                lista[j] = scanner.nextInt();
                listaOrd[j] = lista[j];
            }

            
            for(int y = 0; y < tamanho - 1; y++)
            {
                int maior = y;
                for(int j = (y + 1); j < tamanho; j++)
                {
                    if(listaOrd[maior] > listaOrd[j])
                    {
                        maior = j;
                    }
                }
                int temp = listaOrd[maior];
                listaOrd[maior] = listaOrd[y];
                listaOrd[y] = temp;
            }

            int contador = 0;
            for(int j = 0; j < tamanho; j++)
            {
                if(lista[j] != listaOrd[j])
                {
                    contador++;
                }
            }
            MyIO.println(contador);
        }


        scanner.close();
    }
}