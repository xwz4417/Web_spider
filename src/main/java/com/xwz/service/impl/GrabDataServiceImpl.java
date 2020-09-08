package com.xwz.service.impl;

import com.xwz.bean.Pic;
import com.xwz.mapper.GrabDataMapper;
import com.xwz.service.GrabDataService;
import com.xwz.utils.HttpUtils;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrabDataServiceImpl implements GrabDataService {

    private HttpUtils httpUtils;
    private GrabDataMapper grabDataMapper;

    public GrabDataServiceImpl(HttpUtils httpUtils, GrabDataMapper grabDataMapper) {
        this.httpUtils = httpUtils;
        this.grabDataMapper = grabDataMapper;
    }

    @Override
    public Map<String,Integer> CollectPics(int fid, int start, int stop)throws Exception {
//        long starttime = System.currentTimeMillis();
        Map<String,Integer> map=new HashMap<>();
        List<String> items = httpUtils.GetItems(fid, start, stop);
        map.put("items",items.size());
        int pics_size=0;
        for(String url:items){
            List<Pic> pics = httpUtils.GetPics(url);
            pics_size+=pics.size();
            for(Pic p:pics){
                grabDataMapper.Crawling(p);
            }
        }
        map.put("pics",pics_size);
//        long endtime = System.currentTimeMillis();
//        System.err.println("耗时"+(endtime-start)+"ms");
        return map;
    }
}
