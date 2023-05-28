package tomato.classifier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tomato.classifier.data.ResultData;
import tomato.classifier.dto.DiseaseDto;
import tomato.classifier.entity.Disease;
import tomato.classifier.repository.DiseaseRepository;
import tomato.classifier.repository.TomatoRepository;
import tomato.classifier.service.MainService;


@Controller
@Slf4j
@RequiredArgsConstructor //DI
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String mainView() {
        return "main/mainPage";
    }

    @PostMapping("/predict")
    public String resultView(@RequestBody ResultData data) {

        mainService.save(data);

        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultView(Model model) {

        DiseaseDto result = mainService.result();

        model.addAttribute("tomato", result);

        return "main/resultPage";
    }

    @GetMapping("/map")
    public String mapView() {
        return "main/map";
    }

}
