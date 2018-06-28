package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseType;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;
    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        public String displayAddCheeseForm (Model model){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute(new Cheese());
            -model.addAttribute("cheeseTypes", CheeseType.values());
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String processAddCheeseForm (@ModelAttribute @Valid Cheese newCheese,
        @RequestParam int categoryId,
        Errors errors, Model model){

            if (errors.hasErrors()) {
                model.addAttribute("title", "Add Cheese");
                return "cheese/add";
            }

            Category cat = categoryDao.findOne(categoryId);
            newCheese.setCategory(cat);
            cheeseDao.save(newCheese);
            return "redirect:";
        }
        return "redirect";
    }

}
@RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditCheeseForm(Model model, @PathVariable int cheeseId) {

                model.addAttribute("title", "Edit Cheese");
                model.addAttribute("cheese", cheeseDao.findOne(cheeseId));
                model.addAttribute("categories", categoryDao.findAll());
                return "cheese/edit";
            }

            @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @PathVariable int cheeseId,
                                          @ModelAttribute  @Valid Cheese newCheese,
                                          @RequestParam int categoryId,
                                          Errors errors) {

                if (errors.hasErrors()) {
                    model.addAttribute("title", "Add Cheese");
                    return "cheese/edit";
                }

                Cheese editedCheese = cheeseDao.findOne(cheeseId);
                editedCheese.setName(newCheese.getName());
                editedCheese.setDescription(newCheese.getDescription());
                editedCheese.setCategory(categoryDao.findOne(categoryId));
                cheeseDao.save(editedCheese);

                return "redirect:/cheese";
            }

        }



