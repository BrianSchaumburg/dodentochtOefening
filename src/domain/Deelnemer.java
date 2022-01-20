package domain;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

public class Deelnemer {
    private String naam;
    public LocalTime[] tijd;

    public Deelnemer(String naam) {
        this.naam = naam;
    }
    public void initialisatie(int aantal)
    {
        if(aantal <= 0) throw new IllegalArgumentException();
        tijd = new LocalTime[aantal];
    }
    public void registratie(LocalTime tijd )
    {
        if(this.tijd.length == 0 ) throw new IllegalStateException();
        for(int i = 0; i<this.tijd.length;i++ )
        {
            if(this.tijd[i] == null)
            {   if(i!=0)
                if(this.tijd[i-1].isAfter(tijd)) throw new IllegalStateException();
                this.tijd[i] = tijd;
                break;
            }

        }
    }
    public boolean einde()
    {
        return Arrays.stream(tijd).allMatch(e->e!=null)? true: false;
    }
    public int laatsteControlepunt()
    {   if(einde()) throw new IllegalStateException("De tocht is al beëindigd");
        for(int i = 0 ; i < tijd.length ; i ++ )
            if(tijd[i] ==null)
                return i ;
        throw new IllegalStateException();
    }
    public Duration hoelang()
    {   if(!einde())
        return Duration.between(tijd[0], tijd[laatsteControlepunt()]);
        else
            return Duration.between(tijd[0], tijd[tijd.length-1]);
    }

    @Override
    public String toString() {
       if(einde())
           return naam + " heeft de tocht beindigd in een tijd van " + hoelang().toHoursPart() + ":"+hoelang().toMinutesPart()+ ":"+hoelang().toSecondsPart() ;
        else
            return naam+ "heeft de tocht nog niet beindigd; laatste controlepunt = " + laatsteControlepunt();
    }
}
