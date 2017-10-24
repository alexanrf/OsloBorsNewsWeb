package no.earlywarning;

import java.util.List;

public class Report {
    private final String dateTime;
    private final String utstid;
    private final String message;
    private final List<String> attachments;

    public Report(String dato, String utstid, String melding, List<String> vedlegg){
        this.dateTime = dato;
        this.utstid = utstid;
        this.message = melding;
        this.attachments = vedlegg;
    }

    @Override
    public String toString(){
        String s = message + "\n" + dateTime + "\n" + utstid + "\n";
        for(String v: attachments){
            s += v+"\n";
        }
        return s;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUtstid() {
        return utstid;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getAttachments() {
        return attachments;
    }
}
