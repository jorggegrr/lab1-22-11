package pe.edu.pucp.gtics.lab1221.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.pucp.gtics.lab1221.entity.Distributors;
import pe.edu.pucp.gtics.lab1221.repository.DistributorsRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class DistributorsController {

    @Autowired
    DistributorsRepository distributorsRepository;

    @RequestMapping(value = "/distribuidoras/lista", method = RequestMethod.GET)
    public String listaDistribuidoras (Model model){
        List<Distributors> lista= distributorsRepository.findAll(Sort.by("nombre"));
        model.addAttribute("listaDistrib", lista);
        return "distribuidoras/lista";
    }
    @RequestMapping(value = "distribuidoras/editar", method = RequestMethod.GET)
    public String editarDistribuidoras(Model model, @RequestParam("id") int id){
        Optional<Distributors> optDistribuidoras = distributorsRepository.findById(id);
        if(optDistribuidoras.isPresent()){
            Distributors distribuidora = optDistribuidoras.get();
            model.addAttribute("distribuidora", distribuidora);
            return "distribuidoras/editar";
        }else{
            return "redirect:/distribuidoras/lista";
        }
    }

    @RequestMapping(value="distribuidoras/guardar", method = RequestMethod.POST)
    public String guardarDistribuidora(Distributors distribuidora){
        distributorsRepository.save(distribuidora);
        return "redirect:/distribuidoras/lista";
    }

    public String borrarDistribuidora(){
        return "";
    }

    @RequestMapping(value = "distribuidoras/nuevo", method = RequestMethod.GET)
    public String nuevaDistribuidora(){
        return "distribuidoras/nuevo";
    };


}
