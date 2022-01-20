package domain;

public class Controlepunt {
    private String naam;
    private double breedte, lengte;
    private boolean ehbo ;

    public Controlepunt(String naam, double breedte, double lengte, boolean ehbo) {
        setNaam(naam);
        setBreedte(breedte);
        setLengte(lengte);
        setEhbo(ehbo);
    }

    public void setNaam(String naam) {
       if(naam == null) throw new IllegalArgumentException();
       if(naam.trim().isEmpty()) throw new IllegalArgumentException();
       this.naam = naam;
    }

    public void setBreedte(double breedte) {
       if(breedte<=50 || breedte>=51) throw new IllegalArgumentException();
       this.breedte = breedte;
    }

    public void setLengte(double lengte) {
        if(lengte<=4 || lengte>=5) throw new IllegalArgumentException();
        this.lengte = lengte;
    }

    public void setEhbo(boolean ehbo) {
        this.ehbo = ehbo;
    }

    public String getNaam() {
        return naam;
    }

    public double getBreedtegraad() {
        return breedte;
    }

    public double getLengtegraad() {
        return lengte;
    }

    public boolean isHeeftEHBOPost() {
        return ehbo;
    }
    private String omzetting()
    {
        return ehbo ? " EHBO POST" :" GEEN EHBO POST";
    }
    @Override
    public String toString() {
        return naam + " " + "("+breedte  + ","+lengte + omzetting();
    }
}
