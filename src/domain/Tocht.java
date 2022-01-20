package domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Tocht {
    private LocalDate datum;
    private ArrayList<Deelnemer> deelnemers;
    private Controlepunt[] controlepunts;

    public Tocht(LocalDate datum, int aantal) {
        if(aantal<0 || datum.isBefore(LocalDate.now()))throw new IllegalStateException();
        this.datum = datum;
        this.datum.atTime(LocalTime.of(21,0,0));
            deelnemers = new ArrayList<>();
        controlepunts = new Controlepunt[aantal];

    }
    public Tocht(LocalDate datum)
    {
        if(datum.isBefore(LocalDate.now())) throw new IllegalStateException();
        this.datum = datum;
        this.datum.atTime(LocalTime.of(21,0,0));
        this.controlepunts = new Controlepunt[15];
        deelnemers = new ArrayList<>();
    }
    public int toevoegenControlepunt(Controlepunt controlepunt)
    {
        if(controlepunt == null)throw new IllegalStateException();
        long aantal = Arrays.stream(controlepunts).filter(c->c != null).count();
        if(aantal>=2)
        {
            for(int i = 0; i < controlepunts.length ; i++ )
                if(controlepunts[i] == null)
                {
                    if(controlepunt.isHeeftEHBOPost())
                        controlepunts[i] = controlepunt;
                    else if (controlepunts[i-1].isHeeftEHBOPost() == false && controlepunts[i-2].isHeeftEHBOPost() == false)
                        throw new IllegalStateException(" de ehbo regeling klopt niet");
                    else
                        controlepunts[i] = controlepunt;
                    return i;

                }
        }
        else
        {
            for(int i = 0; i < controlepunts.length ; i++ )
                if(controlepunts[i] == null) {
                    controlepunts[i] = controlepunt;
                    return i;
                }
        }
        throw new IllegalStateException("Er is iets foutgelopen");


    }
    public int aantalVoltooid()
    {
        return (int)deelnemers.stream().filter(c->c.einde()).count();
    }
    public void addDeelnemer(Deelnemer deelnemer)
    {
        if(deelnemer == null)throw new IllegalStateException();
        deelnemer.initialisatie(this.controlepunts.length);
        deelnemers.add(deelnemer);

    }
    public boolean controleEHBO(Controlepunt[] controlepunts)
    {   if(Arrays.stream(controlepunts).filter(c->c!=null).count()>=3) {
        for (int i = 1; i < controlepunts.length; i++) {
            if(controlepunts[i]!=null)
            if (controlepunts[i].isHeeftEHBOPost() == false)
                if (controlepunts[i - 1].isHeeftEHBOPost() == false && controlepunts[i + 1].isHeeftEHBOPost() == false)
                    throw new IllegalArgumentException("de ehbo regeling klopt niet");
        }
    }

        return true;
    }

    @Override
    public String toString() {
       String result = "Dodentocht 2020 " + datum.toString()  + "\n";
       result +="Wandelparcours: "+"\n";
       for(int i= 0; i <controlepunts.length; i ++)
       {
           result+=controlepunts[i].toString() + "\n";
       }
       result+="\n"+"huidig tijdstip " + LocalTime.now().toString() + "\n";
       result+=deelnemers.size() + " zijn ingeschreven, namelijk: " + "\n";
       for(Deelnemer deelnemer: deelnemers )
           result+=deelnemer.toString() + "\n";
       result+= aantalVoltooid() + " deelnemer heeft tocht voltooid";
       return result;
    }
}
