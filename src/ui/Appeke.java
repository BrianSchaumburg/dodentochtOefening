package ui;

import domain.Controlepunt;
import domain.Deelnemer;
import domain.Tocht;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appeke {
    public static void main(String [] args)
    {
        Deelnemer aimee = new Deelnemer("Aimee Backiel");
        Deelnemer tom = new Deelnemer("tom hermans");
        Controlepunt markt = new Controlepunt("Grote markt", 50.112,4.1,true);
        Controlepunt kasteel = new Controlepunt("Arenberg kasteel", 50.112,4.1,false);
        Controlepunt park = new Controlepunt("Zoetwater Park", 50.112,4.1,true);
        Controlepunt campus = new Controlepunt("Campus Proximus", 50.112,4.1,false);
        Controlepunt fout1 = new Controlepunt("fout1", 50.112,4.1,false);
        Controlepunt fout2 = new Controlepunt("fout2", 50.112,4.1,false);

        Tocht tocht = new Tocht(LocalDate.of(2022,8,20),4);
        tocht.toevoegenControlepunt(markt);
        tocht.toevoegenControlepunt(kasteel);
        tocht.toevoegenControlepunt(park);
        tocht.toevoegenControlepunt(campus);

        tocht.addDeelnemer(aimee);
        tocht.addDeelnemer(tom);
        aimee.registratie(LocalTime.of(21,01,0));
        aimee.registratie(LocalTime.of(21,10,0));
        aimee.registratie(LocalTime.of(21,15,0));
        aimee.registratie(LocalTime.of(21,35,0));


        tom.registratie(LocalTime.of(21,01,0));
        tom.registratie(LocalTime.of(21,10,0));
        tom.registratie(LocalTime.of(21,15,0));

        System.out.println(tocht.toString());
        try
        {
            Tocht error = new Tocht(LocalDate.of(2022,8,20),6);
            error.toevoegenControlepunt(markt);
            error.toevoegenControlepunt(kasteel);
            error.toevoegenControlepunt(park);
            error.toevoegenControlepunt(campus);
            error.toevoegenControlepunt(fout1);
            error.toevoegenControlepunt(fout2);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
