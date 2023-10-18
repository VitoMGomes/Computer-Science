public class Aula2 {
    
    public static void main(String[] args)
    {
        Caneta c1 = new Caneta();
        c1.modelo = "BIC Cristal";
        //c1.ponta = 0.5f;
        c1.carga = 80;
        //c1.tampada = true;
        c1.tampar();
        c1.destampar();
        c1.status();
        c1.rabiscar();
    }


}

class Caneta {
    public String modelo;
    public String cor;
    private float ponta;
    protected int carga;
    private boolean tampada;

    public void status(){
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Uma caneta " + this.cor);
        System.out.println("Ponta: " + this.ponta);
        System.out.println("Carga: " + this.carga + "%");
        System.out.println("Está tampada? " + this.tampada);

    }

    public void rabiscar(){
        if(this.tampada == true)
        {
            System.out.println("Erro! A caneta está tampada");
        }
        else
        {
            System.out.println("-------------------------");
        }
    }

    public void tampar(){
        this.tampada = true;
    }

    public void destampar(){
        this.tampada = false;
    }
}

