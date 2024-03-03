package com.present.controller;

import com.present.model.Present;
import com.present.model.PresentForm;
import com.present.service.IPresentService;
import com.present.service.PresentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("presents")
@PropertySource("classpath:upload_file.properties")
public class PresentController {
    @Value("${upload}")
    private String upload;
    private IPresentService presentService = new PresentService();
    @GetMapping("")
    public ModelAndView index(){
        List<Present> presentList = presentService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject(presentList);
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("present",new PresentForm());
        return "create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute PresentForm presentForm){
        MultipartFile file = presentForm.getImg();
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            FileCopyUtils.copy(file.getBytes(),new File(upload+fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Present present = new Present();
        if (presentForm.getId() != 0){
            present.setId(presentForm.getId());
        }
        present.setCode(presentForm.getCode());
        present.setName(presentForm.getName());
        present.setPrice(presentForm.getPrice());
        present.setShip(presentForm.getShip());
        present.setImg(fileName);
        presentService.save(present);
        return "redirect:/presents";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        presentService.remove(id);
        redirect.addFlashAttribute("success","delete success");
        return "redirect:/presents";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("present", presentService.findById(id));
        return "/view";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("present", presentService.findById(id));
        return "/update";
    }
}
