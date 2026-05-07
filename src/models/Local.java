package models;

public class Local {

    private String numLocal;
    private String nomLocal;

    public Local(String numLocal, String nomLocal) {
        this.numLocal = numLocal;
        this.nomLocal = nomLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    @Override
    public String toString() {
        return nomLocal + " (" + numLocal + ")";
    }
}