package com.rencp.mybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindController {

    @GetMapping("find")
    public String tofind() {
        return "find";
    }

}
