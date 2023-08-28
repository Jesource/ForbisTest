package forbis.preselection.assignment.web.controllers;

import forbis.preselection.assignment.web.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/")
@Slf4j
public class MainController {
    @Autowired
    MainPageService mainPageService;


    @GetMapping
    public String welcomePage(Model model) {
        return "index";
    }


    @PostMapping
    public String proceedText(Model model, @RequestParam String inputText) {
        log.info("Text imputed to process: " + inputText);

        List<String> formattedOutput = mainPageService.proceedTextInput(inputText);
//        mainPageService.writeToFile(formattedOutput);
        model.addAttribute("tokens", formattedOutput);

        return "index";
    }
}
