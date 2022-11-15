package com.example.thmodel04.controller;


import com.example.thmodel04.model.Classroom;
import com.example.thmodel04.model.Student;
import com.example.thmodel04.service.classroom.IClassService;
import com.example.thmodel04.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassService classService;

    @ModelAttribute("classrooms")
    public Iterable<Classroom> categories() {
        return classService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listStudent() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("students", studentService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("message", "New student created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("student", student.get());

            return modelAndView;
        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("student", student);
        modelAndView.addObject("message", "Student updated successfully");
        return modelAndView;
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
//        Optional<Student> student1 = studentService.findById(id);
//        if (!student1.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        student.setId(student1.get().getId());
//        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
//    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("student", student.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/delete")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        studentService.remove(student.getId());
        return "redirect:student";
    }
//    @GetMapping("/{id}/view")
//    public ModelAndView view (@PathVariable Long id){
//        Optional<Student> student = studentService.findById(id);
//        if (student.isPresent()){
//        ModelAndView modelAndView = new ModelAndView("/student/view");
//        modelAndView.addObject("student",student.get());
//        return modelAndView;
//        }else {
//            return new ModelAndView("/student/error404");
//        }
//    }
}
