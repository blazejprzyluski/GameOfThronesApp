package com.gameofthronesapp.controllers;

import com.gameofthronesapp.connectivity.CharactersAPIHandler;
import com.gameofthronesapp.datamodel.CharacterSearchObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CharacterController {
    private final CharactersAPIHandler charactersAPIHandler = new CharactersAPIHandler();

    @GetMapping("/characterSearch")
    public String getCharacterSearchView(Model model) {
        model.addAttribute("characterSearchObject", new CharacterSearchObject());
        return "characterSearch";
    }

    @PostMapping("/characterSearch")
    public String getSpecificCharacter(Model model, CharacterSearchObject characterSearchObject) {
        model.addAttribute("character", charactersAPIHandler.getBookCharacter(characterSearchObject.getName()));
        return "character";
    }

    @GetMapping("/character")
    public String getRandomCharacter(Model model) {
        model.addAttribute("character", charactersAPIHandler.getBookCharacter(ThreadLocalRandom.current().nextInt(1, 900 + 1)));
        return "character";
    }
}
