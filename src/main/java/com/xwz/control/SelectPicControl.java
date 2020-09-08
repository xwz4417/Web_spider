package com.xwz.control;

import com.xwz.bean.PageUtils;
import com.xwz.service.LocalDataService;
import com.xwz.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data")
public class SelectPicControl {

    LocalDataService localDataService;

    public SelectPicControl(LocalDataService localDataService) {
        this.localDataService = localDataService;
    }

    @RequestMapping("/all/{page_index}")
    public ModelAndView Show(@PathVariable int page_index, String key) {
        ModelAndView md = new ModelAndView();
        try {

        PageUtils page = new PageUtils();
        //设置页码
        page.setCurrentPage(page_index);
        if (!StringUtils.isBlank(key)) {
            page.setKey("%" + key + "%");
        }
        page = localDataService.SelectDataByAll(page);
        md.addObject("page", page);
        md.setViewName("forum");
        if (!StringUtils.isBlank(key)) {
            key = page.getKey();
            page.setKey(key.substring(key.indexOf("%") + 1, key.lastIndexOf("%")));
        }
            if (page.getRecordCount() == 0) {
                throw new CustomException("没有查询到任何数据");
            }
        }catch (CustomException e){
            md.addObject("msg",e.getMessage());
            e.printStackTrace();
        }
        return md;
    }
}
