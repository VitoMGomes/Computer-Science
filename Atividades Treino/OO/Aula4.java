
class ContaBanco
{
    //atributos
    public int numConta;
    protected String tipo;
    private String dono;
    private float saldo;
    private boolean status;
    
    //metódos
    
    public void estadoAtual() {
        System.out.println("----------------------------------------");
        System.out.println("Conta: " + this.getNumConta());
        System.out.println("Dono: " + this.getDono());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Status: " + this.getStatus());
    }
    
    public void abrirConta(String t) {
        this.setTipo(t);
        this.setStatus(true);
        if(t == "CC")
        {
            this.setSaldo(50);
        }
        else if(t == "CP")
        {
            this.setSaldo(150);
        }
    }
    
    public void fecharConta() {
        if(this.getSaldo() > 0)
        {
            System.out.println("A conta tem dinheiro");
        }
        else if(this.getSaldo() < 0)
        {
            System.out.println("A conta está em débito");
        }
        else
        {
            this.setStatus(false);
        }
    }
    
    public void depositar(float v) {
        if(this.getStatus() == true)
        {
            this.setSaldo(this.getSaldo() + v);
        }
        else
        {
            System.out.println("Erro! Conta fechada");
        }
    }

    public void sacar(float v) {
        if(this.getStatus() == true)
        {
            if(this.getSaldo() >= v)
            {
                this.setSaldo(this.getSaldo() - v);
            }
            else
            {
                System.out.println("Saldo not enough");
            }    
        }
        else
        {
            System.out.println("A conta is closed");
        }
    }
    
    public void pagarMensal() {
        float v = 0;
        
        if(this.getTipo() == "CC")
        {
            v = 12;
        }
        else if(this.getTipo() == "CP")
        {
            v = 20;
        }
        
        if(this.getStatus() == true)
        {
            if(this.getSaldo() > v)
            {
                this.setSaldo(this.getSaldo() - v);
            }
            else
            {
                System.out.println("Saldo not enough");
            }    
        }
        else
        {
            System.out.println("Erro! Conta fechada");
        }
    }
    
    //Metodos especiais
    
    public ContaBanco() {
        this.setSaldo(0);
        this.setStatus(false);
    }
    
    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }
    public int getNumConta() {
        return numConta;
    }
    
    public void setDono(String dono) {
        this.dono = dono;
    }
    public String getDono() {
        return dono;
    }
    
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    public float getSaldo() {
        return saldo;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}

public class Aula4 {
    public static void main(String[] args)
    {
        ContaBanco p1 = new ContaBanco();
        p1.setNumConta(001);
        p1.setDono("Jubileu");
        p1.abrirConta("CC");

        ContaBanco p2 = new ContaBanco();
        p2.setNumConta(002);
        p2.setDono("Creuza");
        p2.abrirConta("CP");

        p1.depositar(100);
        p2.depositar(500);
        p1.sacar(150);
        
        p1.fecharConta();

        p1.estadoAtual();
        p2.estadoAtual();

    }
}