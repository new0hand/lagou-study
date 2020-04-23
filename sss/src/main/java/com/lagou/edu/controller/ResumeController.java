package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    /**
     * Spring容器和SpringMVC容器是有层次的（父子容器）
     * Spring容器：service对象+dao对象
     * SpringMVC容器：controller对象，，，，可以引用到Spring容器中的对象
     */


    @Autowired
    private ResumeService resumeService;

    @GetMapping("/list")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<Resume> resumeList = resumeService.queryResumeList();

        modelAndView.addObject("list", resumeList);
        modelAndView.setViewName("list");

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("create");

        return modelAndView;
    }

    @GetMapping("/insert")
    public String insert(String name, String address, String phone) {
        Resume resume = new Resume();
        resume.setName(name);
        resume.setAddress(address);
        resume.setPhone(phone);

        resumeService.save(resume);

        return "redirect:/resume/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Resume> resume = resumeService.queryResume(id);

        modelAndView.addObject("resume", resume.get());
        modelAndView.setViewName("edit");

        return modelAndView;
    }

    @GetMapping("/update")
    public String update(Long id, String name, String address, String phone) {
        Resume resume = new Resume();
        resume.setId(id);
        resume.setName(name);
        resume.setAddress(address);
        resume.setPhone(phone);

        resumeService.save(resume);

        return "redirect:/resume/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        resumeService.delete(id);

        return "redirect:/resume/list";
    }
}
