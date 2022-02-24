package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.models.JobData.findAll;
import static org.launchcode.techjobs.mvc.models.JobData.findByColumnAndValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    // POST
    //path: results
    //Method params: model, searchType, searchTerm

    //if searchTerm is "all" or "" return all jobs

    //else, call findByColumnAndValue

    //add jobs to model

     //model.addAttribute("columns, columnChoices)
    //Return template name: search

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.equals("") || searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
          // model.addAttribute("jobs", jobs);
        }else {
            jobs = findByColumnAndValue(searchType, searchTerm);
        }
            model.addAttribute("jobs", jobs);
           model.addAttribute("columns", columnChoices);
           model.addAttribute("title", "Search Condition: " + columnChoices.get(searchType) + "Search Term:" +  searchTerm);

        return "search";

}
}
