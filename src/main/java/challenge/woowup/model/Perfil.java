package challenge.woowup.model;

public class Perfil {

    private GestorDeAlertas gestorAlertas;

    public Perfil(GestorDeAlertas gestorAlertas) {
        this.gestorAlertas = gestorAlertas;
    }

    public GestorDeAlertas getGestorAlertas() {
        return gestorAlertas;
    }

    public void setGestorAlertas(GestorDeAlertas gestorAlertas) {
        this.gestorAlertas = gestorAlertas;
    }

}
