package com.example.admin.controller.api;

import com.example.admin.controller.CrudController;
import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.entity.User;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.UserApiRequest;
import com.example.admin.model.network.response.UserApiResponse;
import com.example.admin.model.network.response.UserOrderInfoApiResponse;
import com.example.admin.service.UserApiLogicService;
import com.sun.tools.sjavac.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @GetMapping
    public Header<List<UserApiResponse>> search(@PageableDefault(sort="id", direction= Sort.Direction.ASC, size = 15) Pageable pageable){
        return baseService.search(pageable);
    }

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }
/*
    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = userApiLogicService;
    }*/

/*
    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request){
        //Log.info("{}, {}", request, "ABC");
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header read(@PathVariable(name = "id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return userApiLogicService.delete(id);
    }
*/

}
