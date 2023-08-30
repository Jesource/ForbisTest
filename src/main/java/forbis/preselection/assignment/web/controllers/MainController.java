package forbis.preselection.assignment.web.controllers;

import forbis.preselection.assignment.web.models.InputModel;
import forbis.preselection.assignment.web.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller("/")
@Slf4j
public class MainController {
    @Autowired
    MainPageService mainPageService;


    @GetMapping
    public String welcomePage(Model model) {
        model.addAttribute("inputModel", new InputModel());

        return "index";
    }


    @PostMapping
    public String proceedInput(Model model,
                               @RequestParam char separator,
                               @ModelAttribute InputModel inputModel
    ) {
        log.info("Provided text inputs:\n\t1) {}\n\t2) {}", inputModel.getTextInput1(), inputModel.getTextInput2());
        log.info("Provided file inputs:\n\t1) {}\n\t2) {}", inputModel.getFile1(), inputModel.getFile2());

        List<String> formattedOutput = mainPageService.proceedInput(inputModel, separator);
        model.addAttribute("tokens", formattedOutput);
        model.addAttribute("inputModel", new InputModel());

        return "index";
    }

    @GetMapping("/previous-results")
    public String previousResults(Model model) {
        try {
            model.addAttribute(
                    "previousResults",
                    mainPageService.getPreviousResults()
            );
        } catch (IOException e) {
            log.error("Failed to display previous results: {}", e.getMessage());
        }

        return "previous_results";
    }
}
