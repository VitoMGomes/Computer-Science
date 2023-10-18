#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    char string1[50];
    char string2[50];

    while(scanf("%s", &string1) != EOF)
    {
        scanf("%s", &string2);

        int tamanho1 = strlen(string1);
        int tamanho2 = strlen(string2);


        for(int i = 0; i < tamanho1 && i < tamanho2; i++)
        {
            if(i < tamanho1)
            {
                printf("%c", string1[i]);
            }

            if(i < tamanho2)
            {
                printf("%c", string2[i]);
            }
        }
    }
}