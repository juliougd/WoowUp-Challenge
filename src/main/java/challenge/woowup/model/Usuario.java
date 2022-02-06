package challenge.woowup.model;

public class Usuario {
    private String nombre;
    private Perfil perfil;

    public Usuario(String nombre, Perfil perfil) {
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
