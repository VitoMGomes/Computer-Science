#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool stop(char str[], int tamanho)
{
    bool stop = true;
    if(!(tamanho == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'))
    {
        stop = false;
    }

    return stop;
}

void troca(char str[], int tamanho, int i, char letra1, char letra2)
{
    if(i < tamanho)
    {
        if(str[i] == letra1)
        {
            str[i] = letra2;
        }

        troca(str, tamanho, ++i, letra1, letra2);
    }
}

int main()
{
    char str[1000];
    scanf(" %1000[^\n]", str);
    int tamanho = strlen(str);

    bool stopF = stop(str, tamanho);

    if(!stopF)
    {
        char letra1 = 'a' + rand()%26;
        char letra2 = 'a' + rand()%26;

        troca(str, tamanho, 0, letra1, letra2);
        
        printf("%s\n" , str);

        main();
    }

    return 0;
}