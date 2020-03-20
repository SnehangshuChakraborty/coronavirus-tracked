package com.snehangshu.coronavirustracked.service;

import com.snehangshu.coronavirustracked.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDetectorService {

    public String VIRUS_API_LINK = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<LocationStats> locationStatsList = new ArrayList<>();

    public List<LocationStats> getLocationStatsList() {
        return locationStatsList;
    }

    @PostConstruct
    @Scheduled(cron = "1 * * * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> newlocationStatsList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_API_LINK)).build();
        HttpResponse<String> httpResponse =  client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader stringReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State")); // Province
            locationStats.setCountry(record.get("Country/Region")); //State
            int latestReportedCases = Integer.parseInt(record.get(record.size()-1)); //LatestReport
            int previousDayReportedCases = Integer.parseInt(record.get(record.size()-2));
            locationStats.setLatestReport(latestReportedCases);
            locationStats.setDifferenceFromPreviousDay(latestReportedCases-previousDayReportedCases);
            newlocationStatsList.add(locationStats);
            System.out.println(locationStats);
        }
        this.locationStatsList = newlocationStatsList;
    }

}
