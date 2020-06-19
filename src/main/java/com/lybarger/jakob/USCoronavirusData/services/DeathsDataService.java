package com.lybarger.jakob.USCoronavirusData.services;

import com.lybarger.jakob.USCoronavirusData.models.DeathLocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
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
public class DeathsDataService {

    private static final String DEATH_DATA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_US.csv";

    private List<DeathLocationData> allData = new ArrayList<>();

    public List<DeathLocationData> getAllData() {
        return allData;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void getDeathData() throws IOException, InterruptedException {
        List<DeathLocationData> newData = new ArrayList<>();

        // Create an HTTP request to get the data from the CSV record brought to by the link
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DEATH_DATA)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Create a reader and iterate through the CSV record and create location data objects out of the data
        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for(CSVRecord record : records) {
            DeathLocationData deathLocationData = new DeathLocationData();
            deathLocationData.setCountyAndState(record.get("Combined_Key"));
            deathLocationData.setTotalDeaths(Integer.parseInt(record.get(record.size() - 1)));
            deathLocationData.setCurrentAndPrevDiff(Integer.parseInt(record.get(record.size() - 1)));
            System.out.println(deathLocationData);
            newData.add(deathLocationData);
        }
        this.allData = newData;
    }
}
