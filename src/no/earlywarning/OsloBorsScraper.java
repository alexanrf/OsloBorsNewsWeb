package no.earlywarning;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.List;

class OsloBorsScraper {
    ArrayList<Report> getReports(String from, String to) throws Exception{
        // String concat is bad, but it's pretty minor in this case.
        // Creates the URL to scrape
        String baseUrl = "http://www.newsweb.no/newsweb/search.do?headerSearch=&searchCriteria.categoryIds=&searchSubmitType=searchtype&searchtype=full&searchCriteria.issuerId=-1&searchCriteria.issuerSign=&searchCriteria.instrumentShortName=&searchCriteria.categoryId=1002&searchCriteria.exchangeCode=&_searchCriteria.activeIssuersOnly=&searchCriteria.activeIssuersOnly=true&searchtype.full=Søk";
        String formattedFrom = "&searchCriteria.fromDate=" + from;
        String formattedTo = "&searchCriteria.toDate=" + to;
        String reportUrl = baseUrl + formattedTo + formattedFrom;

        // Creates the list for our reports
        ArrayList<Report> reports = new ArrayList<Report>();


        // Connects to website
        Document doc = Jsoup.connect(reportUrl).get();

        // Table index 6 is the correct one. Several tables on site.
        Element table = doc.select("table").get(6);
        Elements rows = table.select("tr");

        // Stream as it easily skips first element which is a table header
        rows.stream().skip(1).forEach(row->parseRow(row, reports));

        return reports;
    }


    private void parseRow(Element row, ArrayList<Report> reports){
        Elements columns = row.select("td");
        // Sets variables
        String dato = columns.get(0).text();
        String utstid = columns.get(2).text();
        String melding = columns.get(4).text();
        // Gets a list of attachments
        List<String> vedlegg = columns.get(5).select("a[href]").eachAttr("abs:href");

        // Creates a new Report and adds it to our collection.
        Report r = new Report(dato, utstid, melding, vedlegg);
        reports.add(r);
    }


}
