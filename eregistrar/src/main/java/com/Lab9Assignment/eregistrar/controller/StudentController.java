package com.Lab9Assignment.eregistrar.controller;

import com.Lab9Assignment.eregistrar.model.Student;
import com.Lab9Assignment.eregistrar.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/eregistrar/student/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("query", "");
        return "student/list";
    }

    @GetMapping("/eregistrar/student/search")
    public String searchStudents(@RequestParam(name = "query", required = false) String query, Model model) {
        model.addAttribute("students", studentService.search(query));
        model.addAttribute("query", query == null ? "" : query);
        return "student/list";
    }

    @GetMapping("/eregistrar/student/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("editMode", false);
        model.addAttribute("formAction", "/eregistrar/student/register");
        return "student/form";
    }

    @PostMapping("/eregistrar/student/register")
    public String register(@Valid @ModelAttribute("student") Student student,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", false);
            model.addAttribute("formAction", "/eregistrar/student/register");
            return "student/form";
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message",
                "Registered " + student.getFullName() + " (" + student.getStudentNumber() + ").");
        return "redirect:/eregistrar/student/list";
    }

    @GetMapping("/eregistrar/student/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return studentService.findById(id)
                .map(student -> {
                    model.addAttribute("student", student);
                    model.addAttribute("editMode", true);
                    model.addAttribute("formAction", "/eregistrar/student/edit/" + id);
                    return "student/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "No student found with id " + id + ".");
                    return "redirect:/eregistrar/student/list";
                });
    }

    @PostMapping("/eregistrar/student/edit/{id}")
    public String update(@PathVariable("id") Long id,
                          @Valid @ModelAttribute("student") Student student,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            model.addAttribute("formAction", "/eregistrar/student/edit/" + id);
            return "student/form";
        }

        student.setStudentId(id);
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message",
                "Updated " + student.getFullName() + " (" + student.getStudentNumber() + ").");
        return "redirect:/eregistrar/student/list";
    }

    @GetMapping("/eregistrar/student/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        studentService.findById(id).ifPresentOrElse(
                student -> {
                    studentService.deleteById(id);
                    redirectAttributes.addFlashAttribute("message",
                            "Deleted " + student.getFullName() + " (" + student.getStudentNumber() + ").");
                },
                () -> redirectAttributes.addFlashAttribute("error", "No student found with id " + id + ".")
        );
        return "redirect:/eregistrar/student/list";
    }
}
