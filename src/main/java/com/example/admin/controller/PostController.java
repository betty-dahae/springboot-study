package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping("/getPostMethod")
    public String postMethod(@RequestBody SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        return "OK";
    }

    @PutMapping("/putMethod")
    public String putMethod(){
        return "PUT";
    }

    @PatchMapping("/patchMethod")
    public String patchMethod(){
        return "Patch";
    }
}
