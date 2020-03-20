package com.snehangshu.coronavirustracked.controller;

import com.snehangshu.coronavirustracked.models.LocationStats;
import com.snehangshu.coronavirustracked.service.CoronaVirusDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDetectorService coronaVirusDetectorService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats>  locationStats = coronaVirusDetectorService.getLocationStatsList();
        int totalReportedCases = locationStats.stream().mapToInt(locationStats1 -> locationStats1.getLatestReport()).sum();
        int totalNewCases = locationStats.stream().mapToInt(locationStats1 -> locationStats1.getDifferenceFromPreviousDay()).sum();
        model.addAttribute("locationStats",locationStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
