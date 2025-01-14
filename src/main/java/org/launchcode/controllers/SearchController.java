package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value="results")
    public String searchResults (Model model, @RequestParam String searchType, @RequestParam String searchTerm){

        ArrayList<HashMap<String, String>> filteredJobs = JobData.findByColumnAndValue(searchType, searchTerm);
//
//        model.addAttribute("columns", ListController.columnChoices);
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("searchType", searchType);
//
//        return "search";
//
//    }

        if (searchType.equals("all")){
            filteredJobs = JobData.findByValue(searchTerm);
        } else {
            filteredJobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobPosts",filteredJobs);
        model.addAttribute("searchType", searchType);

        return "search";
    }
    // TODO #1 - Create handler to process search request and display results

}
