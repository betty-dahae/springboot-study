package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping( method = RequestMethod.GET, path="/getMethod")
    public String getMethod(){

        return "Hi there";
    }
    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id){
        System.out.println("id : " + id);
        return id;
    }
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){

        return searchParam;
    }
}
