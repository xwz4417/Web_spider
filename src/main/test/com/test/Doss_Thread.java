package com.test;

import com.xwz.utils.UserAgentUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Doss_Thread implements Runnable{


    @Override
    public void run() {
        try {
            CloseableHttpClient build = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://bmbwyz.bdqnht.com/baoming/baoming.php");
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("name", Application_test.randomName(true, 3)));
            pairs.add(new BasicNameValuePair("hometel", Application_test.getTel()));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectionRequestTimeout(500).setSocketTimeout(3 * 1000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("User-Agent", UserAgentUtil.randomUserAgent());
            CloseableHttpResponse execute = build.execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            System.err.println(statusCode);
            if (statusCode == 200) {
                System.err.println("成功");
                execute.close();
            } else {
                System.err.println("失败" + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
