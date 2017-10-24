package no.earlywarning;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{
        OsloBorsScraper s = new OsloBorsScraper();
        ArrayList<Report> reports = s.getReports("01.10.2017", "20.10.2017");

        for(Report r:reports){
            System.out.println(r);
        }
    }
}

/*
Diverse notater for å vise hvordan jeg tenkte når jeg så på oppgaven.


Start med å analyser URLen
http://www.newsweb.no/newsweb/search.do?headerSearch=
&searchCriteria.categoryIds=
&selectedPagenumber=1
&searchSubmitType=searchtype
&searchtype=full
&searchCriteria.issuerId=-1
&searchCriteria.issuerSign=
&searchCriteria.instrumentShortName=
&searchCriteria.categoryId=1002
&searchCriteria.fromDate=01.10.2017
&searchCriteria.toDate=20.10.2017
&searchCriteria.exchangeCode=
&_searchCriteria.activeIssuersOnly=
&searchCriteria.activeIssuersOnly=true
&searchtype.full=Søk

De interessante for vårt tilfelle her er:
&selectedPagenumber=1
&searchCriteria.fromDate=01.10.2017
&searchCriteria.toDate=20.10.2017

Observasjon: Rekkefølge har ingenting å si.
Observasjon: Overflowing page numbers (arg = 5, max sider = 3) defaulter til første side
Observasjon: Overflowing dato input (32.10.2017) defaulter til 1.11.2017.
Observasjon: Ingen dato input defaulter til dagens rapporter.
Observasjon: Max antall rapporter som blir matet ut er 500. Om alt skal scrapes må man knote litt med dato argumenter.

Enkel webside å scrape, ikke noe tullete man må tenke på (som jeg har sett hittil).
Lite exceptionbehandling og, siden de har gjort det ganske robust.


Det som skal hentes ut er:
Dato + tid
Utsteder Id
Alle vedlegg

Funksjonalitet:
Variabel dato input.
Ikke lagring av data.

Data struktur:
Hadde dette vært python hadde jeg brukt en json lignende dictionary. Men siden det er java er det vel like
greit å lage en klasse (som kan extendes senere lett).

OsloBorsScraper
    List<Report> getRapporter(String fraDato, String tilDato)

Report
    String dateTime
    String utstid
    String message
    list<String> attachments

    .toString() //Formated in a nice way
    getters.
    No need for setters as all information is available at object creation.



Observasjoner
    Kan gjøre utstederID til et objekt. I denne sammenhengen er unødvendig.

Disclaimer:
    Liker best å programmere på engelsk, men kan fint bytte over til norsk om dere har noen standarder.
    Kjenner til DateTime objektet, men dropper å gjøre noe som helst med dato behandling.


Ellers; Har lite erfaring med proffesjonell utvikling, mest i skolesammenheng, men jeg har god læringsevne og lærinsgvilje.

 */


