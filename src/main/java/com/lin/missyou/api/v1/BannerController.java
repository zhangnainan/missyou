package com.lin.missyou.api.v1;

import com.lin.missyou.exception.http.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/banner")
public class BannerController {

    @GetMapping("/test")
    public String test(){
        throw new RuntimeException("未知错误");
        //throw new NotFoundException(1000);
       // return "hello,十哥";
    }
}
