package com.xwz.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

public class RequestFacotry {

    //非必须,只是有的网站会以cookie判断是否爬虫
    private  final String COOKIE = "N7ID_2132_saltkey=fq9pGPmj; N7ID_2132_lastvisit=1595097804; N7ID_2132_sid=TSmSaG; N7ID_2132_visitedfid=2; N7ID_2132_st_p=0%7C1595101466%7C9454b97fda6b5fac6f5c28b3e030dffa; N7ID_2132_viewid=tid_5930; N7ID_2132_st_t=0%7C1595101536%7C6e12b33792f75371867b79192e6dfab7; N7ID_2132_forum_lastvisit=D_2_1595101536; N7ID_2132_lastact=1595101537%09misc.php%09seccode; N7ID_2132_seccode=2342337.d6b7d1484fda96108c";

   public   HttpGet getHttpGet(String url){
     HttpGet httpGet = new HttpGet(url);
     httpGet.setHeader("User-Agent", UserAgentUtil.randomUserAgent());
     httpGet.setHeader("Cookie", COOKIE);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).build();
        httpGet.setConfig(config);
     return httpGet;
 }
    public   HttpGet getHttpGet(URI uri){
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("User-Agent", UserAgentUtil.randomUserAgent());
        httpGet.setHeader("Cookie", COOKIE);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(10000).build();
        httpGet.setConfig(config);
        return httpGet;
    }
}
