package com.keiyam.spring_backend.controller.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ControllerPointcuts {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            " && within(com.keiyam.spring_backend.controller..*)")
    public void restControllerClasses() {}

//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
//            "  @annotation(org.springframework.web.bind.annotation.GetMapping) || " +
//            "  @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
//            "  @annotation(org.springframework.web.bind.annotation.PutMapping) || " +
//            "  @annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
//            "  @annotation(org.springframework.web.bind.annotation.PatchMapping)")
//    public void controllerAnnotations() {}

//    @Pointcut("restControllerClasses() || controllerAnnotations()")
//    public void controllerMethods() {}
}
