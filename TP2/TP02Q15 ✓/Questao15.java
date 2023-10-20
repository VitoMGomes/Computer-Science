import java.io.*;
import java.util.*;

// cat pub.in | java Questao05 > saida.out --  "/tmp/players.csv"

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


public class Questao15 
{

    public static boolean stop(String id) {
        boolean stop = true;

        if (id.equals("FIM")) {
            stop = false;
        }

        return stop;
    }

    public static void main(String[] args) {
        Jogador[] jogadores = new Jogador[3991];
        
        int tam = 0;
        String id = MyIO.readLine();
        
        while (!id.equals("FIM")) {
            Jogador jogador = new Jogador();
            jogador = jogador.ler(id);
            jogadores[tam] = jogador;
            
            tam++;
            id = MyIO.readLine();
        }
        
        for (int i = 0; i < 10; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tam; j++) {
                if (jogadores[j].getNome().compareTo(jogadores[minIndex].getNome()) < 0) {
                    minIndex = j;
                }
            }
            Jogador temp = jogadores[minIndex];
            jogadores[minIndex] = jogadores[i];
            jogadores[i] = temp;

        }
        
        for(int i = 0; i < 10; i++)
        {
            jogadores[i].status();
        }
    }
        
}
