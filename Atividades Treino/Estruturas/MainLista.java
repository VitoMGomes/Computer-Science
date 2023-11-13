class Lista {
    private int[] array;
    private int n;

    public Lista() {
        this(6);
    }

    public Lista(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    public void inserirInicio(int x) throws Exception {

        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }

    public void inserirFim(int x) throws Exception {

        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    public void inserir(int x, int pos) throws Exception {

        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    public int removerInicio() throws Exception {

        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        int resp = array[0];
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public int removerFim() throws Exception {

        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }

    public int remover(int pos) throws Exception {

        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        int resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public boolean pesquisar(int x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}

class PrincipalLista {
    public static void main(String[] args) throws Exception {
        System.out.println("==== LISTA ESTATICA ====");
        Lista lista = new Lista();
        int x1, x2, x3;

        lista.inserirInicio(1);
        lista.inserirInicio(0);
        lista.inserirFim(2);
        lista.inserirFim(3);
        lista.inserir(4, 3);
        lista.inserir(5, 2);

        System.out.print("Apos insercoes: ");
        lista.mostrar();

        x1 = lista.removerInicio();
        x2 = lista.removerFim();
        x3 = lista.remover(2);

        System.out.print("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
        lista.mostrar();
    }
}