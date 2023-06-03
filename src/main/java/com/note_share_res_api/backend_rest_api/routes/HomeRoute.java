package com.note_share_res_api.backend_rest_api.routes;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeRoute {
     
    @GetMapping(value="/")
    public InnerHomeRoute getMethodName() {
        return  new InnerHomeRoute("Hello world");
    }
    
}


 
 record InnerHomeRoute(String name) {}
