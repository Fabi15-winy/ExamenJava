package enums;

public enum Horaire {

    H08_10("08:00 - 10:00"),
    H10_12("10:00 - 12:00"),
    H13_15("13:00 - 15:00"),
    H15_17("15:00 - 17:00"),
    H18_20("18:00 - 20:00");

    private String libelle;

    Horaire(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}