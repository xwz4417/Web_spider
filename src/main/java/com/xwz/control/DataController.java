package com.xwz.control;

import com.xwz.bean.PageUtils;
import com.xwz.service.GrabDataService;
import com.xwz.exception.CustomException;
import com.xwz.service.LocalDataService;
import com.xwz.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DataController {

    private GrabDataService grabDataService;
    private LocalDataService localDataService;

    public DataController(GrabDataService grabDataService, LocalDataService localDataService) {
        this.grabDataService = grabDataService;
        this.localDataService = localDataService;
    }

    @RequestMapping("/grab_data")
    public ModelAndView collect_data(String fid, Integer start, Integer stop) {
        ModelAndView md = new ModelAndView();
        try {
            if (start == null || stop == null) {
                throw new CustomException("抓取范围不能为空");
            }
            Map<String, Integer> map = grabDataService.CollectPics(Integer.parseInt(fid), start, stop);
            md.addAllObjects(map);
        } catch (Exception e) {
            md.addObject("msg", e.getMessage());
            e.printStackTrace();
        }
        md.setViewName("collect");
        return md;
    }

    @ResponseBody
    @RequestMapping("/del_pic")
    public Map<String, String> del_pic(String id) {
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isBlank(id)) {
            if (localDataService.Del_Pic(Integer.parseInt(id)) > 0) {
                map.put("ret", "success");
                map.put("msg", "删除成功");
            }
        } else {
            map.put("ret", "fail");
            map.put("msg", "删除失败");
        }
        return map;

    }

    @RequestMapping("download_pic")
    public String Download_Pic(String key, String group, String path) {
        localDataService.Download_Pic(key, group, path);
        return "redirect:/data/all/1";

    }
}
