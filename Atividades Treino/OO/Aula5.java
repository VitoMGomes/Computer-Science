interface Controlador
{
    public abstract void ligar();
    public abstract void desligar();
    public abstract void abrirMenu();
    public abstract void fecharMenu();
    public abstract void maisVolume();
    public abstract void menosVolume();
    public abstract void ligarMudo();
    public abstract void desligarMudo();
    public abstract void play();
    public abstract void pause();
}

class ControleRemoto implements Controlador
{
    private int volume;
    private boolean ligado;
    private boolean tocando;
    
    public ControleRemoto()
    {
        this.volume = 50;
        this.ligado = false;
        this.tocando = false;
    }

    private int getVolume() {
        return volume;
    }
    private void setVolume(int volume) {
        this.volume = volume;
    }

    private boolean isLigado() {
        return ligado;
    }
    private void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    private boolean isTocando() {
        return tocando;
    }
    private void setTocando(boolean tocando) {
        this.tocando = tocando;
    }


    public void ligar() {
        this.setLigado(true);
    }


    public void desligar() {
        this.setLigado(false);
    }


    public void abrirMenu() {
        System.out.println("----MENU----");
        System.out.println("Está ligado? " + this.isLigado());
        System.out.println("Está tocando? " + this.isTocando());
        System.out.println("Volume: " + this.getVolume());

        for(int i = 0; i <= this.getVolume(); i+=10)
        {
            if(i > 1) System.out.print("[]");
        }
    }


    public void fecharMenu() {
        System.out.println("Fechando menu");
    }


    public void maisVolume() {
        if(this.isLigado())
        {
            this.setVolume(getVolume() + 5);
        }
    }


    public void menosVolume() {
        if(this.isLigado())
        {
            this.setVolume(getVolume() - 5);
        }
    }


    public void ligarMudo() {
        if(this.isLigado() && this.getVolume() > 0)
        {
            this.setVolume(0);
        }
    }


    public void desligarMudo() {
        if(this.isLigado() && this.getVolume() == 0)
        {
            this.setVolume(30);
        }
    }


    public void play() {
        if(this.isLigado() && !(this.isTocando()))
        {
            this.setTocando(true);
        }
    }


    public void pause() {
        if(this.isLigado() && this.isTocando())
        {
            this.setTocando(false);
        }
       
    } 
}


public class Aula5 
{
    public static void main(String[] args)
    {
        ControleRemoto c = new ControleRemoto();

        c.ligar();
        c.maisVolume();
        c.maisVolume();
        c.ligarMudo();
        c.desligarMudo();
        c.desligar();
        c.maisVolume();
        c.abrirMenu();
    }
}
