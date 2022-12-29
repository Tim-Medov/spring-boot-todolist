
package enterprise.controllers;

import enterprise.models.Deed;
import enterprise.services.deedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/todolist")
public class TodolistController {

    private deedService deedService;

    @Autowired
    public TodolistController(deedService deedService) {
        this.deedService = deedService;
    }

    @GetMapping
    public String showToDoList(Model model) {

        model.addAttribute("todolist", deedService.showTodolist());

        return "todolist";
    }

    @GetMapping("/{id}")
    public String showDeed(@PathVariable("id") int id, Model model) {

        if (!deedService.existingDeed(id)) {
            return "redirect:/todolist";
        }

        model.addAttribute("deed", deedService.showDeed(id));

        return "deed";
    }

    @GetMapping("/addDeed")
    public String addDeed(@ModelAttribute("deed") Deed deed) {
        return "addDeed";
    }

    @PostMapping()
    public String createDeed(@ModelAttribute("deed") @Valid Deed deed,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addDeed";
        }

        deedService.saveDeed(deed);

        return "redirect:/todolist";
    }

    @GetMapping("/{id}/editDeed")
    public String editDeed(@PathVariable("id") int id, Model model) {

        if (!deedService.existingDeed(id)) {
            return "redirect:/todolist";
        }

        model.addAttribute("deed", deedService.showDeed(id));

        return "editDeed";
    }

    @PatchMapping("/{id}")
    public String updateDeed(@PathVariable("id") int id, @ModelAttribute("deed") @Valid Deed deed,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "editDeed";
        }

        deedService.editDeed(id, deed);

        return "redirect:/todolist";
    }

    @DeleteMapping("/{id}")
    public String deleteDeed(@PathVariable("id") int id) {

        deedService.deleteDeed(id);

        return "redirect:/todolist";
    }

    @GetMapping("/deleteAllDeeds")
    public String deleteAllDeeds() {

        deedService.deleteAllDeeds();

        return "deleteAllDeeds";
    }
}
