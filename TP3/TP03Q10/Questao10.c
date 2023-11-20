#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define stringSize 60

struct Jogador{
    int id;
    char nome[stringSize];
    int altura;
    int peso;
    char universidade[stringSize];
    int anoNascimento;
    char cidadeNascimento[stringSize];
    char estadoNascimento[stringSize];
    struct Jogador *prox;
};
typedef struct Jogador Jogador;

struct Pilha{
    Jogador *primeiro;
    int tam;
};
typedef struct Pilha Pilha;

Pilha Pilhar(){
    Pilha Pilha;
    Pilha.primeiro = (Jogador*) malloc(sizeof(Jogador));
    Pilha.primeiro->prox = NULL;
    Pilha.tam = 0;
    return Pilha;
}

void setId(int id, Jogador* jogadorLido){
    jogadorLido->id = id;
}
// -------------------------------------------------------[GET E SET]
void setNome(char nome[], Jogador* jogadorLido){
    if(nome[0]=='\0'){
        strcpy(jogadorLido->nome, "nao informado");
    } else {
        strcpy(jogadorLido->nome, nome);
    }
}
// -------------------------------------------------------[GET E SET]
void setAltura(int altura, Jogador* jogadorLido){
    jogadorLido->altura = altura;
}
// -------------------------------------------------------[GET E SET]
void setPeso(int peso, Jogador* jogadorLido){
    jogadorLido->peso = peso; 
}
// -------------------------------------------------------[GET E SET]
void setUniversidade(char universidade[], Jogador* jogadorLido){
    if(universidade[0] == '\0'){
           strcpy(jogadorLido->universidade, "nao informado");
    } else {
           strcpy(jogadorLido->universidade, universidade);
    }
}
// -------------------------------------------------------[GET E SET]
void setAnoNascimento(int anoNascimento, Jogador* jogadorLido){
    jogadorLido->anoNascimento = anoNascimento;
}
// -------------------------------------------------------[GET E SET]
void setCidadeNascimento(char cidadeNascimento[], Jogador* jogadorLido){
    if(cidadeNascimento[0] == '\0'){
        strcpy(jogadorLido->cidadeNascimento, "nao informado");
    } else {
        strcpy(jogadorLido->cidadeNascimento, cidadeNascimento);
    }
}
// -------------------------------------------------------[GET E SET]
void setEstadoNascimento(char estadoNascimento[], Jogador* jogadorLido){
    if(estadoNascimento[0] == '\0'){
        strcpy(jogadorLido->estadoNascimento, "nao informado");
    } else {
        strcpy(jogadorLido->estadoNascimento, estadoNascimento);
    }
}
// -------------------------------------------------------[GET E SET]
void setAll(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[], char estadoNascimento[], Jogador* jogadorLido){
    setId(id, jogadorLido);
    setNome(nome, jogadorLido);
    setAltura(altura, jogadorLido);
    setPeso(peso, jogadorLido);
    setUniversidade(universidade, jogadorLido);
    setAnoNascimento(anoNascimento, jogadorLido);
    setCidadeNascimento(cidadeNascimento, jogadorLido);
    setEstadoNascimento(estadoNascimento, jogadorLido);
}

Jogador* clone(Jogador *j){
    Jogador* jClone = (Jogador*) malloc(sizeof(Jogador));
    setAll(j->id, j->nome, j->altura, j->peso, j->universidade, j->anoNascimento, j->cidadeNascimento, j->estadoNascimento, jClone);
    return jClone;
}

void split (char lido[], char final[], int virg){
    char aux;
    int posL = 0, posF = 0;
    while(virg>0){
        if(lido[posL++]==',')
            virg--;
    }

    aux = lido[posL++];
    while( aux != ',' && aux != '\n' ) {
        final[posF++] = aux;
        aux = lido[posL++];
    }
    final[posF] = '\0';
}

Jogador* ler(int id){
    Jogador* jogador = NULL;

    FILE* arq = fopen("/tmp/players.csv", "r");
    if(arq != NULL)
    {
        char idChar[stringSize];
        char nome[stringSize];
        char altura[stringSize];
        char peso[stringSize];
        char universidade[stringSize];
        char nascimento[stringSize];
        char cidade[stringSize];
        char estado[stringSize];
        char line[8*stringSize];

        while(fgetc(arq) != '\n');
        do{
            int tam = 0;
            char c;
            do{
                c = fgetc(arq);
                line[tam++] = c;
            }while ( c != '\n'  && c != EOF);
            split(line, idChar, 0);
        } while (atoi(idChar) != id && line[0] != EOF);
        
        if(line[0] == EOF){
            printf("Id inexistente");
        } else {
            jogador = (Jogador*) malloc(sizeof(Jogador));

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

Jogador* pop(Pilha *Pilha){
    Jogador* rem = NULL;
    if(Pilha->tam > 0){
        rem = clone(Pilha->primeiro->prox);
        free(Pilha->primeiro->prox);
        Pilha->primeiro->prox = Pilha->primeiro->prox->prox;
        Pilha->tam--;
    }
    return rem;
}

void push(Jogador *push, Pilha *Pilha){
    if (push != NULL){
        push->prox = Pilha->primeiro->prox;
        Pilha->primeiro->prox = push;
        Pilha->tam++;
    }
}

void status(Jogador* jogador, int pos){
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);

}

void menuPilha(char in, Pilha* Pilha){
    int param1;
    if(in == 'I'){
        scanf("%d", &param1);
        push(ler(param1), Pilha);
    } else if(in == 'R'){
        Jogador* rem = pop(Pilha);
        if(rem != NULL){
            printf("(R) %s\n", rem->nome);
        }
    }
}

int main() {
    char in[20]; 
    Pilha Pilha = Pilhar();

    scanf("%s", in);
    while(strcmp(in, "FIM")){
        push(ler(atoi(in)), &Pilha);
        scanf("%s", in);
    }
    
    int ops;
    scanf("%d", &ops);
    while(ops-- > 0){
        scanf("%s", in);
        menuPilha(in[0], &Pilha);
    }
    
    Jogador *bef = NULL;
    for(int i=0; i < Pilha.tam; i++){
        Jogador *tmp = NULL;
        for(Jogador *j = Pilha.primeiro; j != bef; j = j->prox){
            tmp = j;
        }
        bef = tmp;
        status(bef, i);
    }

    return 0;
}