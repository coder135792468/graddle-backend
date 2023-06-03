package com.note_share_res_api.backend_rest_api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
     
    @GetMapping(value="/")
    public InnerHomeRoute getMethodName() {
        return  new InnerHomeRoute("Hello world");
    }
    
}


 
 record InnerHomeRoute(String name) {}
