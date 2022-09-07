package pe.edu.pucp.gtics.lab1221.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.pucp.gtics.lab1221.entity.Games;
import pe.edu.pucp.gtics.lab1221.repository.GamesRepository;
import pe.edu.pucp.gtics.lab1221.repository.PlatformsRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GamesController {
    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    PlatformsRepository platformsRepository;

    @RequestMapping(value="juegos/lista", method = RequestMethod.GET)
    public String listaJuegos (Model model){
        List<Games> listaJuegos = gamesRepository.findAll(Sort.by("precio"));
        model.addAttribute("listaJuegos", listaJuegos);
        return "juegos/lista";
    }

    @RequestMapping(value = "juegos/editar", method = RequestMethod.GET)
    public String editarJuegos(Model model, @RequestParam("id") int id){
        Optional<Games> optJuego = gamesRepository.findById(id);
        if(optJuego.isPresent()){
            Games juego = optJuego.get();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", platformsRepository.findAll());
            return "juegos/editar";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @RequestMapping(value = "juegos/guardar", method = RequestMethod.POST)
    public String guardarJuegos(Games juegos){
        gamesRepository.save(juegos);
        return "redirect:/juegos/lista";
    }

}
