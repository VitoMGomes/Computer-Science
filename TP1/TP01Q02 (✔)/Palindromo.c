#include <stdio.h>
#include <string.h>    
#include <stdbool.h>
/*
** Printa se é palindromo ou não ao receber
** a bool resultado da função verifyPalin
** Imprime SIM caso verdadeiro e NAO caso não-verdadeiro(falso)
*/
void write(bool result)
{
    if(result)
    {
        puts("SIM");
    }
    else
    {
        puts("NAO");
    }
}

/*
** Recebe a string e o tamanho dela, então verifica 
** se é palindromo e retorna;
*/
bool verifyPalin(char linha[], int tamanho)
{
    bool pal = true;//pré-definido como verdadeiro, para ser necessario somente uma operação depois

    for(int i = 0; i < tamanho / 2; i++)
    {
        if(linha[i] != linha[tamanho - 1 - i])
        {
            i = tamanho;//break disfarçado
            pal = false;//muda o valor caso o laço verifique a string como não palindromo
        }
    }

    return pal;
}

/*
** Recebe a string e mede o tamanho para retorna-lo
*/
int getTamanho(char linha[])
{
    int tamanho = strlen(linha);

    return tamanho;
}

/*
** Define quando o laço deve ser interrompido ou não
** comparando a string com a flag 'FIM'
*/

bool fim(char linha[])
{
    bool pal = strcmp(linha, "FIM");

    return pal;
}

int main()
{

    char linha[1000];
    bool stop;

    do{
        scanf(" %1000[^\n]", linha);//lê a string/frase

        stop = fim(linha);//define a condição da Flag

        if(stop)//Só executará as funções caso a string esteja diferente de 'FIM'
        {
          int tamanho = getTamanho(linha); 

          bool ehPalin = verifyPalin(linha, tamanho);

          write(ehPalin);
        }



    }while(stop);//para o codigo caso stop seja verdadeiro na função

}