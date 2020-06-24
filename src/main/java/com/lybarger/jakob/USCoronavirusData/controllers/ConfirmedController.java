package com.lybarger.jakob.USCoronavirusData.controllers;

import com.lybarger.jakob.USCoronavirusData.models.ConfirmedLocationData;
import com.lybarger.jakob.USCoronavirusData.services.ConfirmedCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfirmedController {

    @Autowired
    ConfirmedCasesDataService virusDataService;

    @GetMapping("/Cases")
    public String confirmedCases(Model model) {
        List<ConfirmedLocationData> allData = virusDataService.getAllData(); // Populate a list of all of the ConfirmedLocationData objects
        int totalNewCases = allData.stream().mapToInt(ConfirmedLocationData::getTotalCasesReported).sum(); // Calculate the total confirmed cases of all ConfirmedLocationData objects
        int todayYesterdayDiff = allData.stream().mapToInt(ConfirmedLocationData::getCurrentAndPrevDiff).sum(); // Calculate the difference in total confirmed cases between two most recent days
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("todayYesterdayDiff", todayYesterdayDiff);
        model.addAttribute("locationData", virusDataService.getAllData());
        return "ConfirmedCases";
    }
}
