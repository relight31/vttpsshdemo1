package vttp.paf.sshdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.paf.sshdemo.model.Gif;
import vttp.paf.sshdemo.services.GiphyService;

@Controller
public class GiphyController {

    @Autowired
    private GiphyService service;

    @GetMapping(path = "/search")
    public String giphySearch(
            Model model,
            @RequestParam String phrase,
            @RequestParam int limit,
            @RequestParam String rating) {
        List<Gif> gifs = service.searchGifs(phrase, limit, rating);
        model.addAttribute("gifs", gifs);
        model.addAttribute("phrase", phrase.toUpperCase());
        return "results";
    }
}
