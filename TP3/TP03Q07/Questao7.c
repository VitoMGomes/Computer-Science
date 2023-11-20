#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define strSize 60
#define TamFila 6

struct Jogador{
    int id;
    char nome[strSize];
    int altura;
    int peso;
    char universidade[strSize];
    int anoNascimento;
    char cidadeNascimento[strSize];
    char estadoNascimento[strSize];
    struct Jogador *prox;
};
typedef struct Jogador Jogador;

struct Fila{
    Jogador *primeiro;
    Jogador *ultimo;
    int tam;
};
typedef struct Fila Fila;

Fila newFila(){
    Fila fila;
    fila.primeiro = fila.ultimo = (Jogador*) malloc(sizeof(Jogador));
    fila.tam = 0;
    
    return fila;
}

// -------------------------------------------------------[GET E SET]
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
    Jogador* Clone = (Jogador*) malloc(sizeof(Jogador));
    setAll(j->id, j->nome, j->altura, j->peso, j->universidade, j->anoNascimento, j->cidadeNascimento, j->estadoNascimento, Clone);
    return Clone;
}

void split (char read[], char final[], int virgulas){
    char aux;
    int posL = 0, posF = 0;
    while(virgulas>0){
        if(read[posL++]==',')
            virgulas--;
    }

    aux = read[posL++];
    while( aux != ',' && aux != '\n' ) {
        final[posF++] = aux;
        aux = read[posL++];
    }
    final[posF] = '\0';
}

Jogador* ler(int id){
    Jogador* jogador = NULL;

    FILE* arq = fopen("/tmp/players.csv", "r");
    if(arq != NULL)
    {
        char idChar[strSize];
        char nome[strSize];
        char altura[strSize];
        char peso[strSize];
        char universidade[strSize];
        char nascimento[strSize];
        char cidade[strSize];
        char estado[strSize];
        char line[8*strSize];

        
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
        
        if(line[0] != EOF) 
        {
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

float media(Fila *fila){
    Jogador *i = fila->primeiro->prox;
    float soma = 0;
    for(int j=0; j<fila->tam; j++, i = i->prox){
        soma += i->altura;
    }
    return (float) soma/fila->tam;
}

Jogador* pop(Fila *fila){
    Jogador* out = NULL;
    if(fila->tam > 0){
        out = clone(fila->primeiro->prox);
        free(fila->primeiro->prox);
        fila->primeiro->prox = fila->primeiro->prox->prox;
        if(fila->tam == 1){
            fila->ultimo = fila->primeiro;    
        }
        fila->tam--;
    } else {
        puts("Erro - fila vazia\n");
    }
    return out;
}

void push(Jogador *add, Fila *fila){
    if (add != NULL){
        if(fila->tam == 5){
            pop(fila);
        }
        fila->ultimo->prox = add;
        fila->ultimo = fila->ultimo->prox;
        fila->tam++;
        printf("%.0f\n", media(fila));
    } else {
        puts("Erro - jogador inexistente\n");
    }
}

void status(Jogador* jogador, int pos){
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);

}

void menu(char id, Fila* fila){
    int primeiro;
    if(id == 'I'){
        scanf("%d", &primeiro);
        push(ler(primeiro), fila);
    } else if(id == 'R'){
        Jogador* out = pop(fila);
        if(out != NULL){
            printf("(R) %s\n", out->nome);
        }
    }
}

int main() {
    char id[10]; 
    Fila fila = newFila();

    scanf("%s", id);
    while(strcmp(id, "FIM")){
        push(ler(atoi(id)), &fila);
        scanf("%s", id);
    }
    
    int ops;
    scanf("%d", &ops);
    while(ops-- > 0){
        scanf("%s", id);
        menu(id[0], &fila);
    }
    
    Jogador* i = fila.primeiro->prox;
    for(int j=0; j < fila.tam; j++, i = i->prox){
        status(i, j);
    }

    return 0;
}