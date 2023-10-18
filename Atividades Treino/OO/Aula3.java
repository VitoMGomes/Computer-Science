public class Aula3 {
    public static void main(String[] args)
    {
        Caneta c1 = new Caneta("Nic", "Amarela", 0.4f);
        c1.status();
        Caneta c2 = new Caneta("Borbolona", "Verde", 2.0f);
        c2.status();
    }

}

class Caneta
{
    private String modelo;
    private float ponta;
    private String cor;
    private boolean tamapada;

    public Caneta(String m, String c, float p) //metódo construtor 
    { 
        this.modelo = m;
        this.cor = c;
        this.setPonta(p);
        this.tampar();
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String m) {
        this.modelo = m;
    }

    public float getPonta() {
        return this.ponta;
    }

    public void setPonta(float p) {
        this.ponta = p;
    }

    public void tampar() {
        this.tamapada = true;
    }

    public void destampar() {
        this.tamapada = false;
    }



    public void status() {
        System.out.println("Modelo: " + this.getModelo());
        System.out.println("Ponta: " + this.getPonta());
        System.out.println("Cor: " + this.cor);
        System.out.println("Tampada: " + this.tamapada);
    }
}