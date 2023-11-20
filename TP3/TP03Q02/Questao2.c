#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define srtSize 100

struct Jogador
{
    int id;
    char nome[srtSize];
    int altura;
    int peso;
    char universidade[srtSize];
    int anoNascimento;
    char cidadeNascimento[srtSize];
    char estadoNascimento[srtSize];
};
typedef struct Jogador Jogador;
Jogador *jogador[3991];

int numPlayers = 0;

// ----------------[SETTERS]--------------------
void setId(int id, Jogador *jogadorLido)
{
    jogadorLido->id = id;
}
// ----------------[SETTERS]--------------------
void setNome(char nome[], Jogador *jogadorLido)
{
    if (nome[0] == '\0')
    {
        strcpy(jogadorLido->nome, "nao informado");
    }
    else
    {
        strcpy(jogadorLido->nome, nome);
    }
}
// ----------------[SETTERS]--------------------
void setAltura(int altura, Jogador *jogadorLido)
{
    jogadorLido->altura = altura;
}
// ----------------[SETTERS]--------------------
void setPeso(int peso, Jogador *jogadorLido)
{
    jogadorLido->peso = peso;
}
// ----------------[SETTERS]--------------------
void setUniversidade(char universidade[], Jogador *jogadorLido)
{
    if (universidade[0] == '\0')
    {
        strcpy(jogadorLido->universidade, "nao informado");
    }
    else
    {
        strcpy(jogadorLido->universidade, universidade);
    }
}
// ----------------[SETTERS]--------------------
void setAnoNascimento(int anoNascimento, Jogador *jogadorLido)
{
    jogadorLido->anoNascimento = anoNascimento;
}
// ----------------[SETTERS]--------------------
void setCidadeNascimento(char cidadeNascimento[], Jogador *jogadorLido)
{
    if (cidadeNascimento[0] == '\0')
    {
        strcpy(jogadorLido->cidadeNascimento, "nao informado");
    }
    else
    {
        strcpy(jogadorLido->cidadeNascimento, cidadeNascimento);
    }
}
// ----------------[SETTERS]--------------------
void setEstadoNascimento(char estadoNascimento[], Jogador *jogadorLido)
{
    if (estadoNascimento[0] == '\0')
    {
        strcpy(jogadorLido->estadoNascimento, "nao informado");
    }
    else
    {
        strcpy(jogadorLido->estadoNascimento, estadoNascimento);
    }
}
// ----------------[SETTERS]--------------------
void define(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[], char estadoNascimento[], Jogador *jogadorLido)
{
    setId(id, jogadorLido);
    setNome(nome, jogadorLido);
    setAltura(altura, jogadorLido);
    setPeso(peso, jogadorLido);
    setUniversidade(universidade, jogadorLido);
    setAnoNascimento(anoNascimento, jogadorLido);
    setCidadeNascimento(cidadeNascimento, jogadorLido);
    setEstadoNascimento(estadoNascimento, jogadorLido);
}
// ----------------[CLONE]--------------------
Jogador *clone(Jogador *jogador)
{
    Jogador *Clone = (Jogador *)malloc(sizeof(Jogador));
    define(jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->universidade, jogador->anoNascimento, jogador->cidadeNascimento, jogador->estadoNascimento, Clone);
    return Clone;
}

void split(char in[], char Final[], int virgulas)
{
    char tmp;
    int L = 0, F = 0;
    while (virgulas > 0)
    {
        if (in[L++] == ',') virgulas--;
    }

    tmp = in[L++];
    while (tmp != ',' && tmp != '\n')
    {
        Final[F++] = tmp;
        tmp = in[L++];
    }
    Final[F] = '\0';
}

Jogador *ler(int id)
{
    Jogador *jogador = NULL;

    FILE *arq = fopen("/tmp/players.csv", "r");
    if (arq != NULL)
    {
        char idChar[srtSize];
        char nome[srtSize];
        char altura[srtSize];
        char peso[srtSize];
        char universidade[srtSize];
        char nascimento[srtSize];
        char cidade[srtSize];
        char estado[srtSize];
        char line[8 * srtSize + 7];

        do
        {
            int tam = 0;
            char c;
            do
            {
                c = fgetc(arq);
                line[tam++] = c;
            } while (c != '\n' && c != EOF);
            split(line, idChar, 0);
        } while (atoi(idChar) != id && line[0] != EOF);

        if (line[0] != EOF)
        {
            jogador = (Jogador *)malloc(sizeof(Jogador));

            setId(atoi(idChar), jogador);

            split(line, nome, 1);
            setNome(nome, jogador);

            split(line, altura, 2);
            setAltura(atoi(altura), jogador);

            split(line, peso, 3);
            setPeso(atoi(peso), jogador);

            split(line, universidade, 4);
            setUniversidade(universidade, jogador);

            split(line, nascimento, 5);
            setAnoNascimento(atoi(nascimento), jogador);

            split(line, cidade, 6);
            setCidadeNascimento(cidade, jogador);

            split(line, estado, 7);
            setEstadoNascimento(estado, jogador);
        }
    }
    fclose(arq);
    return jogador;
}

void iI(Jogador *add)
{
    if (add == NULL)
    {
    }
    else
    {
        for (int i = numPlayers; i > 0; i--)
        {
            jogador[i] = jogador[i - 1];
        }
        jogador[0] = add;
        numPlayers++;
    }
}
void iF(Jogador *add)
{
    if (add == NULL)
    {
    }
    else
    {
        jogador[numPlayers++] = add;
    }
}
void iE(Jogador *add, int pos)
{
    if (pos <= numPlayers && add != NULL)
    {
        for (int i = numPlayers; i > pos; i--)
        {
            jogador[i] = jogador[i - 1];
        }
        jogador[pos] = add;
        numPlayers++;
    }
}

Jogador *rI()
{
    Jogador *out = NULL;
    if (numPlayers != 0)
    {
        out = clone(jogador[0]);
        for (int i = 0; i < numPlayers - 1; i++)
        {
            jogador[i] = jogador[i + 1];
        }
    }

    numPlayers--;
    return out;
}
Jogador *rF()
{
    Jogador *out = NULL;
    if (numPlayers != 0)
    {
        out = clone(jogador[numPlayers - 1]);

        numPlayers--;
    }
    return out;
}
Jogador *rE(int pos)
{
    Jogador *out = NULL;
    if (pos < numPlayers)
    {
        out = (Jogador *)malloc(sizeof(Jogador));
        out = clone(jogador[pos]);
        for (int i = pos; i < numPlayers - 1; i++)
        {
            jogador[i] = jogador[i + 1];
        }
    }

    numPlayers--;
    return out;
}

void printJogador(Jogador *jogador, int pos)
{

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

void acoes(char in[])
{
    int primeiro;
    int segundo;
    if (in[0] == 'I')
    {
        if (in[1] == 'I')
        {
            scanf("%d", &primeiro);
            iI(ler(primeiro));
        }
        else if (in[1] == 'F')
        {
            scanf("%d", &primeiro);
            iF(ler(primeiro));
        }
        else if (in[1] == '*')
        {
            scanf("%d %d", &primeiro, &segundo);
            iE(ler(segundo), primeiro);
        }
    }
    else if (in[0] == 'R')
    {
        Jogador *out = NULL;
        if (in[1] == 'I')
        {
            out = rI();
        }
        else if (in[1] == 'F')
        {
            out = rF();
        }
        else if (in[1] == '*')
        {
            scanf("%d", &primeiro);
            out = rE(primeiro);
        }

        if (out != NULL)
        {
            printf("(R) %s\n", out->nome);
        }
    }
}

int main()
{
    char id[20];

    scanf("%s", id);
    while (strcmp(id, "FIM"))
    {
        Jogador *tmp = ler(atoi(id));
        if (tmp != NULL)
        {

            iF(tmp);
        }
        scanf("%s", id);
    }

    int ops;
    scanf("%d", &ops);
    while (ops-- > 0)
    {
        scanf("%s", id);
        acoes(id);
    }

    for (int i = 0; i < numPlayers; i++)
    {
        printJogador(jogador[i], i);
    }

    return 0;
}