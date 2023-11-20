import java.io.*;
import java.util.*;

// cat pub.in | java Questao01 > saida.out  --  "/tmp/players.csv"

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    Jogador prox;

    public void status() {
        MyIO.println("[" + this.getId() + " ## " + this.getNome() + " ## " + this.getAltura() + " ## " + this.getPeso()
                + " ## " + this.getAnoNascimento() + " ## " + this.getUniversidade() + " ## "
                + this.getCidadeNascimento() + " ## " + this.getEstadoNascimento() + "]");
    }

    public void define(int id, String nome, int altura, int peso, String universidade, int ano, String cidade,
            String estado, Jogador prox) {
        this.setId(id);
        this.setNome(nome);
        this.setAltura(altura);
        this.setPeso(peso);
        this.setUniversidade(universidade);
        this.setAnoNascimento(ano);
        this.setCidadeNascimento(cidade);
        this.setEstadoNascimento(estado);
        this.prox = prox;
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
            BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv")); // leitura do arquivo
            String line; // string que recebe cada linha do arquivo
            int i = 0;
            while (((line = br.readLine()) != null) && i == 0) // enquanto nao chegar no fim do arquivo
            {
                String[] data = line.split(",", -1); // separa os dados da linha em um array de strings
                if (data.length < 8) // se a linha nao tiver todos os dados, pula para a proxima
                {
                    continue;
                }
                if (data[0].equals(id)) // se o id do jogador for igual ao id passado como parametro, escreve os dados
                                        // no objeto
                {
                    x = new Jogador(); // cria um novo objeto
                    int idInt = Integer.parseInt(data[0]);
                    String nome = data[1];
                    int altura = Integer.parseInt(data[2]);
                    int peso = Integer.parseInt(data[3]);
                    String universidade = data[4];
                    int ano = Integer.parseInt(data[5]);
                    String cidade = data[6];
                    String estado = data[7];

                    x.define(idInt, nome, altura, peso, universidade, ano, cidade, estado, null); // escreve os dados no
                    // objeto

                    i = 1;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
    }

    // ----------------[CLONE]--------------------
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

class Lista {
    Jogador primeiro;
    Jogador ultimo;
    int tam;

    Lista() {
        tam = 0;
        primeiro = ultimo = new Jogador();
    }

    public void iI(Jogador jogador) {
        if (ultimo == primeiro)
            primeiro.prox = jogador;
        jogador.prox = primeiro.prox;
        primeiro.prox = jogador;
        jogador = null;

        tam++;
    }

    public void iF(Jogador jogador) {
        jogador.prox = null;
        ultimo.prox = jogador;
        ultimo = jogador;

        tam++;
    }

    public void iE(Jogador jogador, int pos) throws Exception {
        if (pos > tam) throw new Exception("invalid p");
        Jogador posIns = primeiro;
        for (int i = 0; i < pos; i++) posIns = posIns.prox;
        jogador.prox = posIns.prox;
        posIns.prox = jogador;
        jogador = null;

        tam++;
    }

    public Jogador rI() throws Exception {
        if (primeiro == ultimo) throw new Exception("empty");
        Jogador tmp = primeiro;
        primeiro = primeiro.prox;
        tmp.prox = null;
        tmp = null;
        tam--;
        return primeiro;
    }

    public Jogador rF() throws Exception {
        if (primeiro == ultimo) throw new Exception("empty");
        Jogador i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);
        ultimo = i;
        i = ultimo.prox;
        ultimo.prox = null;
        tam--;
        return i;
    }

    public Jogador rE(int pos) throws Exception {
        if (primeiro == ultimo) throw new Exception("empty");
        else if (pos >= tam) throw new Exception("invalid p");
        Jogador posOut = primeiro;
        for (int i = 0; i < pos; i++) posOut = posOut.prox;
        Jogador out = posOut.prox;
        posOut.prox = posOut.prox.prox;
        out.prox = null;
        tam--;
        return out;
    }

    // metodo para mostrar na tela
    public void status() {
        int pos = 0;
        for (Jogador i = primeiro.prox; i != null; i = i.prox, pos++) {
            MyIO.println("[" + pos + "]"
                    + " ## " + i.getNome()
                    + " ## " + i.getAltura()
                    + " ## " + i.getPeso()
                    + " ## " + i.getAnoNascimento()
                    + " ## " + i.getUniversidade()
                    + " ## " + i.getCidadeNascimento()
                    + " ## " + i.getEstadoNascimento() + " ##");
        }
    }
}

public class Questao05 {

    public static Lista lista = new Lista();

    public static void methodsLista(String[] parte) {
        try {
            Jogador jogador = new Jogador();
            if (parte[0].equals("II")) {
                lista.iI(jogador.ler(parte[1]));
            } else if (parte[0].equals("IF")) {
                lista.iF(jogador.ler(parte[1]));
            } else if (parte[0].equals("I*")) {
                lista.iE(jogador.ler(parte[2]), Integer.parseInt(parte[1]));
            } else if (parte[0].equals("RI")) {
                Jogador out;
                out = lista.rI();
                MyIO.println("(R) " + out.getNome());
            } else if (parte[0].equals("RF")) {
                Jogador out;
                out = lista.rF();
                MyIO.println("(R) " + out.getNome());
            } else if (parte[0].equals("R*")) {
                Jogador out;
                out = lista.rE(Integer.valueOf(parte[1]));
                MyIO.println("(R) " + out.getNome());
            }
        } catch (Exception e) {
            MyIO.println(e.getMessage());
        }
    }

    public static boolean stop(String id) {
        boolean stop = true;

        if (id.equals("FIM")) {
            stop = false;
        }

        return stop;
    }

    public static void main(String args[]) {
        String pubin = MyIO.readLine();

        while (stop(pubin)) {
            Jogador jogador = new Jogador();
            jogador = jogador.ler(pubin);
            lista.iF(jogador);

            pubin = MyIO.readLine();
        }

        int mod = Integer.parseInt(MyIO.readLine());

        for (int i = 0; i < mod; i++) {
            methodsLista(MyIO.readLine().split(" "));
        }
        lista.status();

    }

}