#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define srtSize 100
#define TF 6
int init = 0;
int end = 0;

struct Jogador{
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
Jogador* jogador[TF];


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
        char idD[srtSize];
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
            split(line, idD, 0);
        } while (atoi(idD) != id && line[0] != EOF);

        if (line[0] != EOF)
        {
            jogador = (Jogador *)malloc(sizeof(Jogador));

            setId(atoi(idD), jogador);

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
// ----------------[TIRAR]--------------------
Jogador* pop(){
    Jogador* out = NULL;
    if(init != end){
        out = clone(jogador[init]);
        free(jogador[init]);
        init = (init+1) % TF;
    }

    return out;
}
// ----------------[INSERIR]--------------------
void push(Jogador* add){
    if (add != NULL){
        if((end + 1) % TF == init)
            pop();
        jogador[end] = add;
        end = (end+1) % TF;
        int soma = 0;
        int elementos = 0;
        for(int i = init; i != end; i = (i+1) % TF){
            soma += jogador[i] -> altura;
            elementos++;
        }
        printf("%.0f\n", (float)soma/elementos);
    }
}
void status(Jogador* jogador, int pos){
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);

}

void menu(char in){
    int param1;
    if(in == 'I'){
        scanf("%d", &param1);
        push(ler(param1));
    } else if(in == 'R'){
        Jogador* out = NULL;
            out = pop();
        if(out != NULL){
            printf("(R) %s\n", out->nome);
        }
    }
}

int main() {
    char in[20]; 
    int qtdeOperacoes;

    scanf("%s", in);
    while(strcmp(in, "FIM")){
        Jogador* tmp = ler(atoi(in));
        if(tmp != NULL){
            push(tmp);
        }
        scanf("%s", in);
    }
    
    scanf("%d", &qtdeOperacoes);
    qtdeOperacoes--;
    while(qtdeOperacoes-- > 0){
        scanf("%s", in);
        menu(in[0]);
    }
    menu(in[0]);
    for(int i = init; i != end; i = (i+1) % TF){
         status(jogador[i], (i-init) % TF);
    }
    return 0;
}