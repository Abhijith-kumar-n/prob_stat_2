package com.jsonMapper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class fileController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/updatemapping")
    public String updatemapping() {
        return "updatemapping";
    }
}
