package com.lybarger.jakob.USCoronavirusData.controllers;

import com.lybarger.jakob.USCoronavirusData.models.DeathLocationData;
import com.lybarger.jakob.USCoronavirusData.services.DeathsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DeathsController {

    @Autowired
    DeathsDataService deathsDataService;

    @GetMapping("/Deaths")
    public String confirmedDeathsController(Model model) {
        List<DeathLocationData> allData = deathsDataService.getAllData(); // Populate a list of all of the DeathLocationData objects
        int totalDeaths = allData.stream().mapToInt(DeathLocationData::getTotalDeaths).sum(); // Calculate the total deaths of all DeathLocationData objects
        int prevAndCurDiff = allData.stream().mapToInt(DeathLocationData::getCurrentAndPrevDiff).sum(); // Calculate the difference in total deaths between the two most recent days
        model.addAttribute("totalDeaths", totalDeaths);
        model.addAttribute("prevAndCurDiff", prevAndCurDiff);
        model.addAttribute("deathData", deathsDataService.getAllData());
        return "ConfirmedDeaths";
    }
}
