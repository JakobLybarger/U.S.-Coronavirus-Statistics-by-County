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

    @GetMapping("/ConfirmedCases")
    public String ConfirmedCases(Model model) {
        List<ConfirmedLocationData> allData = virusDataService.getAllData();
        int totalNewCases = allData.stream().mapToInt(ConfirmedLocationData::getTotalCasesReported).sum();
        int todayYesterdayDiff = allData.stream().mapToInt(ConfirmedLocationData::getCurrentAndPrevDiff).sum();
        System.out.println(totalNewCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("todayYesterdayDiff", todayYesterdayDiff);
        model.addAttribute("locationData", virusDataService.getAllData());
        return "ConfirmedCases";
    }
}
