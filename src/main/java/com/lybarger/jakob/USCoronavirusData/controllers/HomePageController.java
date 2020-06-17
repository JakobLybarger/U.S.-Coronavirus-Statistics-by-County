package com.lybarger.jakob.USCoronavirusData.controllers;

import com.lybarger.jakob.USCoronavirusData.models.LocationData;
import com.lybarger.jakob.USCoronavirusData.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationData> allData = virusDataService.getAllData();
        int totalNewCases = allData.stream().mapToInt(LocationData::getTotalCasesReported).sum();
        System.out.println(totalNewCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("locationData", virusDataService.getAllData());
        return "home";
    }
}
