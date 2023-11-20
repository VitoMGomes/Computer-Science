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

class Pilha extends Jogador{
    Jogador primeiro;

    //construtor
    Pilha(){
        primeiro = null;
    }
    
    public void push(Jogador jogador){
        if(primeiro != null){
            jogador.prox = primeiro;
        }
        primeiro = jogador;
        jogador = null;
    }

    public Jogador pop() throws Exception{
        if(primeiro == null)
            throw new Exception("empty");
        Jogador tmp = primeiro;
        primeiro = primeiro.prox;
        tmp.prox = null;
        return tmp;
    }

    public void status(){
        int pos = 0;
        Pilha pilha = new Pilha();
        for(Jogador i = primeiro; i != null; i = i.prox){
            pilha.push(i.clone());
        }
        for(Jogador i = pilha.primeiro; i != null; i = i.prox, pos++){
            MyIO.println("[" + pos + "]"
                    + " ## " + i.getNome()
                    + " ## " + i.getAltura()
                    + " ## " + i.getPeso()
                    + " ## " + i.getAnoNascimento()
                    + " ## " + i.getUniversidade()
                    + " ## " + i.getCidadeNascimento()
                    + " ## " + i.getEstadoNascimento()+ " ##" );
        }
    }
}

public class Questao6 {

    public static Pilha pilha = new Pilha();

    public static void methodsPilha(String[] parte) {
        try{
            Jogador jogador = new Jogador();
            if(parte[0].toUpperCase().equals("I")){
                pilha.push(jogador.ler(parte[1]));         
            }
            else if(parte[0].toUpperCase().equals("R")){
                Jogador out;
                out = pilha.pop();
                MyIO.println("(R) " + out.getNome());
            } 
        }catch (Exception e){
            MyIO.println("Um erro ocorreu: " + e.getMessage());
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
            pilha.push(jogador);

            pubin = MyIO.readLine();
        }

        int mod = Integer.parseInt(MyIO.readLine());

        for (int i = mod - 1; i > 0; i--) {
            methodsPilha(MyIO.readLine().split(" "));
        }
        methodsPilha("R".split(" "));

        pilha.status();

    }

}
