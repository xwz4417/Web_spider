package com.xwz.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {
    @RequestMapping("/")
    public ModelAndView DefaultView() {
        ModelAndView md = new ModelAndView();
        md.addObject("hi", "Surprise mother fuck ");
        md.setViewName("index");
        return md;
    }

    @RequestMapping("/collect")
    public String jump() {
        return "collect";
    }
@RequestMapping("/about")
    public String About(){
        return "about";
}
}
