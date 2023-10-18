#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//determina se string corresponde com a flag
bool stop(char str[], int tamanho) 
{
  bool stop = true;

  if (!(tamanho == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M')) {
    stop = false;
  }

  return stop;
}

//verifica se é pal ou não, caso não seja retorna false, caso seja retorna true
bool isPal(char *str, int i, int tamanho) 
{
    if (i >= tamanho / 2)
    {
        return true;
    }
    else if (str[i] != str[tamanho - i - 1])
    {
        return false;
    }
    else
    {
        return isPal(str, i + 1, tamanho);
    }
}

int main() 
{
  char str[1000];
  scanf(" %1000[^\n]", str);
  int tamanho = strlen(str);

  bool stopF = stop(str, tamanho);//chama a fun

  if (!stopF)//entra somente se nao corresponder a flag
  {
    bool pal = isPal(str, 0, tamanho);//chama a string para verificar enviando a string e o valor inicial de i

    if(pal == true)
    { 
        puts("SIM");
    }
    else
    {
        puts("NAO");
    }

    main();//chama novamente a main
  }

  return 0;
}