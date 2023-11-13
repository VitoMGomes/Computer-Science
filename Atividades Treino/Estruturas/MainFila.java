class Fila {
    private int[] array;
    private int primeiro;
    private int ultimo;

    public Fila() {
        this(6);
    }

    public Fila(int tamanho) {
        array = new int[tamanho + 1];
        primeiro = ultimo = 0;
    }

    public void inserir(int x) throws Exception {

        if (((ultimo + 1) % array.length) == primeiro) {
            throw new Exception("Erro ao inserir!");
        }

        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length;
    }

    public int remover() throws Exception {

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }

        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        return resp;
    }

    public void mostrar() {
        System.out.print("[ ");

        for (int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
            System.out.print(array[i] + " ");
        }

        System.out.println("]");
    }

    public void mostrarRec() {
        System.out.print("[ ");
        mostrarRec(primeiro);
        System.out.println("]");
    }

    public void mostrarRec(int i) {
        if (i != ultimo) {
            System.out.print(array[i] + " ");
            mostrarRec((i + 1) % array.length);
        }
    }

    public boolean isVazia() {
        return (primeiro == ultimo);
    }
}

class MainFila {
    public static void main(String[] args) throws Exception {
        System.out.println("==== FILA ESTATICA ====");
        Fila fila = new Fila();
        int x1, x2, x3;

        fila.inserir(5);
        fila.inserir(7);
        fila.inserir(8);
        fila.inserir(9);

        System.out.println("Apos insercoes(5, 7, 8, 9): ");
        fila.mostrar();

        x1 = fila.remover();
        x2 = fila.remover();

        System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
        fila.mostrar();

        fila.inserir(3);
        fila.inserir(4);

        System.out.println("Apos insercoes(3, 4): ");
        fila.mostrar();

        x1 = fila.remover();
        x2 = fila.remover();
        x3 = fila.remover();

        System.out.println("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
        fila.mostrar();

        fila.inserir(4);
        fila.inserir(5);

        System.out.println("Apos insercoes(4, 5): ");
        fila.mostrar();

        x1 = fila.remover();
        x2 = fila.remover();

        System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
        fila.mostrar();

        fila.inserir(6);
        fila.inserir(7);

        System.out.println("Apos insercoes(6, 7): ");
        fila.mostrar();

        x1 = fila.remover();

        System.out.println("Apos remocao (" + x1 + "): ");
        fila.mostrar();
    }
}
