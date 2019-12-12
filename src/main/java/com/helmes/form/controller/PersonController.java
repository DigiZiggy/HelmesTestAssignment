package com.helmes.form.controller;

import com.helmes.form.model.Person;
import com.helmes.form.model.Sector;
import com.helmes.form.service.PersonServiceInterface;
import com.helmes.form.service.SectorServiceInterface;
import com.helmes.form.validator.PersonFormValidator;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonFormValidator personFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personFormValidator);
    }

    private PersonServiceInterface personService;

    @Autowired
    public void setPersonService(PersonServiceInterface personService) {
        this.personService = personService;
    }

    private SectorServiceInterface sectorService;

    @Autowired
    public void setSectorService(SectorServiceInterface sectorService) {
        this.sectorService = sectorService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/people";
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String showAllPeople(Model model) {

        logger.debug("showAllPeople()");
        model.addAttribute("people", personService.findAll());
        return "people/list";
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public String saveOrUpdatePerson(@ModelAttribute("personForm") @Validated Person person,
                                     BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdatePerson() : {}", person);

        if (result.hasErrors()) {
            populateSectors(model);
            return "people/personform";
        } else {

            personService.saveOrUpdate(person);

            redirectAttributes.addFlashAttribute("css", "success");
            if(person.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Person added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Person updated successfully!");
            }

            // POST/REDIRECT/GET
            return "redirect:/people/" + person.getId();
        }
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.GET)
    public String showPersonForm(Model model) {

        logger.debug("showPersonForm()");

        Person person = new Person();

        model.addAttribute("personForm", person);

        populateSectors(model);

        return "people/personform";
    }

    @RequestMapping(value = "/people/{id}/update", method = RequestMethod.GET)
    public String showUpdatePersonForm(@PathVariable("id") int id, Model model) {

        logger.debug("showUpdatePersonForm() : {}", id);

        Person person = personService.findPersonById(id);
        model.addAttribute("personForm", person);

        populateSectors(model);

        return "people/personform";
    }

    @RequestMapping(value = "/people/{id}/delete", method = RequestMethod.POST)
    public String deletePerson(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        logger.debug("deletePerson() : {}", id);

        personService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Person is deleted!");

        return "redirect:/people";
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public String showPerson(@PathVariable("id") int id, Model model) {

        logger.debug("showPerson() id: {}", id);

        Person person = personService.findPersonById(id);
        if (person == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Person not found");
        }
        model.addAttribute("person", person);

        return "people/showperson";
    }

    private void populateSectors(Model model) {
        List<Sector> sectorObjects = sectorService.findAll();

        Map<Integer, String> sectors = new LinkedHashMap<Integer, String>(){{
            put(1, "");
            put(19, "");
            put(18, "");
            put(6, "");
            put(342, "");
            put(43, "");
            put(42, "");
            put(40, "");
            put(39, "");
            put(437, "");
            put(378, "");
            put(13, "");
            put(389, "");
            put(385, "");
            put(390, "");
            put(98, "");
            put(101, "");
            put(392, "");
            put(394, "");
            put(341, "");
            put(99, "");
            put(12, "");
            put(94, "");
            put(91, "");
            put(224, "");
            put(97, "");
            put(271, "");
            put(269, "");
            put(230, "");
            put(93, "");
            put(508, "");
            put(227, "");
            put(11, "");
            put(67, "");
            put(263, "");
            put(267, "");
            put(542, "");
            put(75, "");
            put(62, "");
            put(69, "");
            put(66, "");
            put(9, "");
            put(54, "");
            put(556, "");
            put(559, "");
            put(55, "");
            put(57, "");
            put(53, "");
            put(560, "");
            put(5, "");
            put(148, "");
            put(150, "");
            put(145, "");
            put(7, "");
            put(44, "");
            put(45, "");
            put(8, "");
            put(337, "");
            put(51, "");
            put(47, "");
            put(2, "");
            put(25, "");
            put(35, "");
            put(28, "");
            put(581, "");
            put(576, "");
            put(121, "");
            put(122, "");
            put(22, "");
            put(141, "");
            put(21, "");
            put(111, "");
            put(114, "");
            put(112, "");
            put(113, "");
            put(3, "");
            put(37, "");
            put(29, "");
            put(33, "");
        }};
        for (Sector sector : sectorObjects) {
            String decodedName= StringEscapeUtils.unescapeHtml4(sector.getName());
            sectors.put(sector.getId(), decodedName);
        }
        model.addAttribute("sectorsList", sectors);
    }
}
