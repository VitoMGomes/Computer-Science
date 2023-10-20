import java.io.*;
import java.util.*;

// cat pub.in | java Questao13 > saida.out --  "/tmp/players.csv"

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public void status() {
        MyIO.println("[" + this.getId() + " ## " + this.getNome() + " ## " + this.getAltura() + " ## " + this.getPeso()
                + " ## " + this.getAnoNascimento() + " ## " + this.getUniversidade() + " ## "
                + this.getCidadeNascimento() + " ## " + this.getEstadoNascimento() + "]");
    }

    public void define(int id, String nome, int altura, int peso, String universidade, int ano, String cidade, String estado) {
        setId(id);
        setNome(nome);
        setAltura(altura);
        setPeso(peso);
        setUniversidade(universidade);
        setAnoNascimento(ano);
        setCidadeNascimento(cidade);
        setEstadoNascimento(estado);
    }

    // --------------------[CONSTRUCT]--------------------
    public Jogador() {
        this.setId(0);
        this.setNome("nao informado");
        this.setAltura(0);
        this.setPeso(0);
        this.setUniversidade("nao informado");
        this.setAnoNascimento(0);
        this.setCidadeNascimento("nao informado");
        this.setEstadoNascimento("nao informado");
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int ano, String cidade, String estado) {
        this.setId(id);
        this.setNome(nome);
        this.setAltura(altura);
        this.setPeso(peso);
        this.setUniversidade(universidade);
        this.setAnoNascimento(ano);
        this.setCidadeNascimento(cidade);
        this.setEstadoNascimento(estado);
    }

    // -------------------------------------------------------[GET E SET]
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // -------------------------------------------------------[GET E SET]

    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // -------------------------------------------------------[GET E SET]

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    // -------------------------------------------------------[GET E SET]

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    // -------------------------------------------------------[GET E SET]

    public void setUniversidade(String universidade) {
        if (universidade != "")
            this.universidade = universidade;
    }

    public String getUniversidade() {
        return universidade;
    }

    // -------------------------------------------------------[GET E SET]

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    // -------------------------------------------------------[GET E SET]

    public void setCidadeNascimento(String cidadeNascimento) {
        if (cidadeNascimento != "")
            this.cidadeNascimento = cidadeNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    // -------------------------------------------------------[GET E SET]

    public void setEstadoNascimento(String estadoNascimento) {
        if (estadoNascimento != "")
            this.estadoNascimento = estadoNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public Jogador ler(String id) {
        Jogador x = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv"));
            String line;
            int i = 0;
            while (((line = br.readLine()) != null) && i == 0) {
                String[] data = line.split(",",-1);
                if (data.length < 8) {
                    continue;
                }
                if (data[0].equals(id)) {
                    x = new Jogador();
                    int idInt = Integer.parseInt(data[0]);
                    String nome = data[1];
                    int altura = Integer.parseInt(data[2]);
                    int peso = Integer.parseInt(data[3]);
                    String universidade = data[4];
                    int ano = Integer.parseInt(data[5]);
                    String cidade = data[6];
                    String estado = data[7];
                    
                    x.define(idInt, nome, altura, peso, universidade, ano, cidade, estado);
                    
                    i = 1;
                }
            } 
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
    }
    //----------------[CLONE]--------------------
    public Jogador clone() {
        Jogador clonado = new Jogador();
        clonado.id = this.id;
        clonado.nome = this.nome;
        clonado.altura = this.altura;
        clonado.peso = this.peso;
        clonado.universidade = this.universidade;
        clonado.anoNascimento = this.anoNascimento;
        clonado.cidadeNascimento = this.cidadeNascimento;
        clonado.estadoNascimento = this.estadoNascimento;
        
        return clonado;
    }
    
}

public class Questao13
{
    public static Jogador[] jogadores = new Jogador[3991];
    public static int comparacoes = 0, movimentacoes = 0;

    private static void merge(int left, int mid, int right) 
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Jogador[] auxLeft = new Jogador[n1];
        Jogador[] auxRight = new Jogador[n2];

        for (int i = 0; i < n1; i++) {
            auxLeft[i] = jogadores[left + i];
        }
        for (int j = 0; j < n2; j++) {
            auxRight[j] = jogadores[mid + 1 + j];
        }

        int i = 0, j = 0, l = left;
        while (i < n1 && j < n2) {
            if (auxLeft[i].getUniversidade().compareTo(auxRight[j].getUniversidade()) <= 0) {
                jogadores[l] = auxLeft[i];
                i++;
                comparacoes++;
                movimentacoes++;
            } else {
                jogadores[l] = auxRight[j];
                j++;
                comparacoes++;
                movimentacoes++;
            }
            l++;
           comparacoes+=2;
        }

        while (i < n1) {
            jogadores[l] = auxLeft[i];
            i++;
            l++;
            comparacoes++;
            movimentacoes++;

        }

        while (j < n2) {
            jogadores[l] = auxRight[j];
            j++;
            l++;
            comparacoes++;
            movimentacoes++;
        }

    }

    public static void mergeSort(int left, int right) {
        if (left < right) {
            int meio = (left + right) / 2;
    
            mergeSort(left, meio); 
            mergeSort(meio + 1, right); 
    
            merge(left, meio, right);
        }
    }

    public static void main(String[] args) {
        File arq = new File("800643_mergesort.txt");


        int tam = 0;
        String id = MyIO.readLine();

        while (!id.equals("FIM")) {
            Jogador jogador = new Jogador();
            jogador = jogador.ler(id);
            jogadores[tam] = jogador;
            
            tam++;
            id = MyIO.readLine();
        }
  
        long inicio = new Date().getTime();//marca o inicio do programa

        mergeSort(0, tam - 1);
        
        long fim = new Date().getTime();//marca a hora de finalização

        //ordena por nome dentro das universidades
        for(int i = 1; i < tam; i++){
            Jogador temp = jogadores[i];
            int j = i - 1;
            if(jogadores[j].getNome().compareTo(temp.getNome()) > 0)
            {
                while((j >= 0) && (jogadores[j].getUniversidade().compareTo(temp.getUniversidade()) == 0) && jogadores[j].getNome().compareTo(temp.getNome()) > 0){
                    jogadores[j + 1] = jogadores[j];
                    j--;
                }
            }
          jogadores[j + 1] = temp;
        }

        //System.out.println(tam);
        for(int i = 0; i < tam; i++)
        {
            jogadores[i].status();
        }


        long execucao = fim - inicio;
        try {
            FileWriter fw = new FileWriter(arq);
            fw.write("Matrícula: 800643 |" + "\tTempo: "+ execucao/1000f + "s |" + " \tComparações: " + comparacoes + " | \tMovimentações: " + movimentacoes);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}