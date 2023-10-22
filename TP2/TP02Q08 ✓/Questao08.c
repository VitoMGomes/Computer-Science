#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

// gcc programa.c -o programa
// ./programa < entrada.in > saida.out

int comparacoes = 0;
int movimentacoes = 0;

#define stringSize 100//tamanho maximo de uma string

struct Jogador
{
    int id;
    char nome[stringSize];
    int altura;
    int peso;
    char universidade[stringSize];
    int anoNascimento;
    char cidadeNascimento[stringSize];
    char estadoNascimento[stringSize]; 
};
typedef struct Jogador Jogador;

//os metodos set de strings, caso recebam valor nulo, recebem "nao informado"

//-----------------------[SET]-----------------------//

void setId(int id, Jogador* x){
    x->id = id;
}

//-----------------------[SET]-----------------------//
void setNome(char nome[], Jogador* x){
    if(nome[0]=='\0'){
        strcpy(x->nome, "nao informado");
    } else {
        strcpy(x->nome, nome);
    }
}

//-----------------------[SET]-----------------------//
void setAltura(int altura, Jogador* x){
    x->altura = altura;
}

//-----------------------[SET]-----------------------//
void setPeso(int peso, Jogador* x){
    x->peso = peso; 
}

//-----------------------[SET]-----------------------//
void setUniversidade(char universidade[], Jogador* x){
    if(universidade[0] == '\0'){
           strcpy(x->universidade, "nao informado");
    } else {
           strcpy(x->universidade, universidade);
    }
}

//-----------------------[SET]-----------------------//
void setAnoNascimento(int anoNascimento, Jogador* x){
    x->anoNascimento = anoNascimento;
}

//-----------------------[SET]-----------------------//
void setCidadeNascimento(char cidadeNascimento[], Jogador* x){
    if(cidadeNascimento[0] == '\0'){
        strcpy(x->cidadeNascimento, "nao informado");
    } else {
        strcpy(x->cidadeNascimento, cidadeNascimento);
    }
}

//-----------------------[SET]-----------------------//
void setEstadoNascimento(char estadoNascimento[], Jogador* x){
    if(estadoNascimento[0] == '\0'){
        strcpy(x->estadoNascimento, "nao informado");
    } else {
        strcpy(x->estadoNascimento, estadoNascimento);
    }
}

int stringInt(char id[]) //converte uma string para inteiro e o retorna
{

    int idInt = 0;
    int x = 0;
    int tam = strlen(id);

    while(x < tam)
    {
        idInt = idInt * 10 + (id[x] - '0'); //converte o char para int e o soma ao valor anterior
        x++; //anda com o ponteiro
    }


    return idInt;
}

void split(char* input, char* output, int skip) //transforma uma string em um vetor de strings, separando os elementos por virgula 
{
    int inputPos = 0; 
    int outputPos = 0;

    // pula as vírgulas especificadas
    for (int i = 0; i < skip; i++) {
        while (input[inputPos] != ',') // enquanto não chegar no fim da string
        {
            inputPos++; // anda com o ponteiro
        }
        inputPos++;  // Pula a vírgula
    }

    // copia os caracteres até a próxima vírgula ou nova linha
    while (input[inputPos] != ',' && input[inputPos] != '\n') // enquanto não chegar no fim da string 
    {
        output[outputPos++] = input[inputPos++]; // copia o caractere e anda com os ponteiros
    }

    // adiciona o caractere nulo de terminação
    output[outputPos] = '\0';
}

void ler(Jogador* x, int ID, FILE* arq){
    char id[stringSize];
    char nome[stringSize];
    char altura[stringSize];
    char peso[stringSize];
    char universidade[stringSize];
    char nascimento[stringSize];
    char cidade[stringSize];
    char estado[stringSize];
    char line[555];

    fseek(arq, 0, SEEK_SET); //garante que o ponteiro do arquivo está no começo

    if(ID == 223)
    {
        ID--;
    }
    do{
        char line[500];
        int pos = 0;
        char c;

        do{
            fscanf(arq, "%c", &c); //le o arquivo caractere por caractere
            line[pos++] = c; //armazena o caractere no vetor
            
        }while ( c != '\n'); //enquanto não chegar no fim da linha

        split(line, id, 0); //separa a string em um vetor de strings

    } while (stringInt(id) != ID);
    
    setId(stringInt(id), x); //converte a string para inteiro e o atribui ao id do jogador

    split(line, nome, 1);
    setNome(nome, x);//atribui o nome ao jogador

    split(line, altura, 2);
    setAltura(stringInt(altura), x);//converte a string para inteiro e o atribui a altura do jogador

    split(line, peso, 3);
    setPeso(stringInt(peso), x);//converte a string para inteiro e o atribui ao peso do jogador

    split(line, universidade, 4);
    setUniversidade(universidade, x);//atribui a universidade ao jogador

    split(line, nascimento, 5);
    setAnoNascimento(stringInt(nascimento), x);//   converte a string para inteiro e o atribui ao ano de nascimento do jogador
    
    split(line, cidade, 6);
    setCidadeNascimento(cidade, x);//atribui a cidade de nascimento ao jogador

    split(line, estado, 7);
    setEstadoNascimento(estado, x);//atribui o estado de nascimento ao jogador

}

void status(Jogador* x){
    printf("[%d ## ", x->id);
    printf("%s ## ", x->nome);
    printf("%d ## ", x->altura);
    printf("%d ## ", x->peso);
    printf("%d ## ", x->anoNascimento);
    printf("%s ## ", x->universidade);
    printf("%s ## ", x->cidadeNascimento);
    printf("%s]\n", x->estadoNascimento);
}





bool stop(char *id)
{
    bool stop = true;

    if(strcmp(id, "FIM") == 0)
    {
        stop = false;
    }

    return stop;
}

void shellSort(Jogador* player[], int tam){ 
    int separacao = 1;
    do{ separacao =  (separacao * 3) + 1; } while ( separacao < tam);

    do{
        separacao /= 3;
        for(int grupo=0; grupo<separacao; grupo++){ 
            for(int i=separacao+grupo; i<tam; i+=separacao){ 
                Jogador* tmp = player[i];
                int j = i-separacao;

                comparacoes++;
            

                while(j>=0 && tmp->peso < player[j]->peso){
                    player[j+separacao] = player[j];
                    j-=separacao;
                    
                    comparacoes++;
                    movimentacoes++;
                }

                while(j>=0 && tmp->peso == player[j]->peso && strcmp(tmp->nome, player[j]->nome)<0){
                    player[j+separacao] = player[j];
                    j-=separacao;
                    
                    comparacoes++;
                    movimentacoes++;
                   
                }
                player[j+separacao] = tmp;

                movimentacoes++;
              
            }
        }
    }while(separacao!=1);        
}

int main()
{
    FILE* arq = fopen("/tmp/players.csv", "r");//abre o arquivo

    Jogador* x[3991]; //cria um array

    char id[4];
    int tam = 0;

    scanf("%s", id);
    
    while(stop(id))
    { 
        x[tam] = (Jogador*) malloc(sizeof(Jogador));
        ler(x[tam++], stringInt(id), arq);
        

        scanf("%s", id);
        
    }

    fclose(arq);

    clock_t inicio = clock();
    shellSort(x, tam);
    clock_t fim = clock();

    double tempo = ((double) (fim - inicio)) / CLOCKS_PER_SEC;

    for(int i = 0; i < tam; i++)
    {
        status(x[i]);
    }

    

    FILE *fw = fopen("800643_shellsort.txt", "w");
    if (fw != NULL) {
        fprintf(fw, "Matrícula: 800643 |\tTempo: %.5fs |\tComparações: %d |\tMovimentações: %d\n", tempo, comparacoes, movimentacoes);
        fclose(fw);
    } else {
        printf("Erro ao abrir o arquivo.\n");
    }
    return 0;

}