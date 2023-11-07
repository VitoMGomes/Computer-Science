#include <stdlib.h>
#include <stdio.h>

int main()
{
    int cartela;
    scanf("%d", &cartela);

    int tamanho;
    scanf("%d", &tamanho);

    int final;
    scanf("%d", &final);

    int matriz[cartela][tamanho];

    for (int i = 0; i < cartela; i++)
    {
        for (int j = 0; j < tamanho; j++)
        {
            int n;
            scanf("%d", &n);
            matriz[i][j] = n;
        }
    }

    int valores[final];
    for (int i = 0; i < final; i++)
    {
        int n;
        scanf("%d", &n);
        valores[i] = n;
    }

    int contador[cartela];

    for (int i = 0; i < cartela; i++)
    {
        contador[i] = 0;
        int iguais = 0;

        for (int j = 0; j < tamanho; j++)
        {
            for (int z = 0; z < final; z++)
            {
                contador[i]++;
                if (matriz[i][j] == valores[z])
                {
                    iguais++;
                    if (iguais == tamanho)
                    {
                        z = final;
                    }
                }
            }
        }
    }

    int menorContador = contador[0];
    int cartelaVencedora = 0;

    for (int i = 1; i < cartela; i++)
    {
        if (contador[i] < menorContador)
        {
            menorContador = contador[i];
            cartelaVencedora = i;
        }
    }

    printf("%d", cartelaVencedora);

    return 0;
}
