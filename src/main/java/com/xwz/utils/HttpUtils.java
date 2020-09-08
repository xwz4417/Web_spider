package com.xwz.utils;

import com.xwz.bean.Pic;
import com.xwz.exception.CustomException;
import com.xwz.mapper.LocalDataMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HttpUtils {

    private final String ROOT = "http://a1b2c3d4.6699a.xyz:16982/";
    private CloseableHttpClient httpClient;
    private RequestFacotry requestFacotry;

    public HttpUtils(CloseableHttpClient httpClient, RequestFacotry requestFacotry) {
        this.httpClient = httpClient;
        this.requestFacotry = requestFacotry;
    }

    //获取页面帖子链接
    public List<String> GetItems(int fid, int star, int stop) throws Exception {
        List<String> items = new ArrayList<>();
        for (int i = star; i <= stop; i++) {

            URIBuilder builder = new URIBuilder("http://a1b2c3d4.6699a.xyz:16982/forum.php?mod=forumdisplay");
            //http://a1b2c3d4.6699a.xyz:16982/forum.php?mod=forumdisplay&fid=2
            //?=&fid=2&page=3
            builder.setParameter("fid", String.valueOf(fid));
            builder.setParameter("page", String.valueOf(i));
            HttpGet httpGet = requestFacotry.getHttpGet(builder.build());

            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 304) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                Document document = Jsoup.parse(result);
                Elements tbodys = document.select("#threadlisttableid>tbody");
                int x = (i == 1) ? 4 : 0;
                for (; x < tbodys.size(); x++) {
                    Element e = tbodys.get(x);
                    if ("emptb".equals(e.attr("class"))) {
                        continue;
                    }
                    String href = e.select(".icn>a").get(0).attr("href");
                    items.add(ROOT + href);
                }
                System.err.println("第" + i + "页,抓取" + tbodys.size() + "条");
                response.close();
            } else {
                throw new CustomException("爬取失败");
            }
        }
        return items;
    }

    //获取帖子链接里的详情
    public List<Pic> GetPics(String url) throws Exception {
        List<Pic> pics = new LinkedList<>();
        try {
            HttpGet httpGet = requestFacotry.getHttpGet(url);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                String result = EntityUtils.toString(entity);
                Document doc = Jsoup.parse(result);

                String title = doc.select(".ts").first().text();

                System.out.println("正在爬取：" + title);
                Elements imgs = doc.select("img");
                for (Element img : imgs) {
                    if (img.attr("src").contains("https://pic.x6x6x6.xyz/")) {
                        String src = img.attr("src");
                        //抓取到的https链接爬虫不能直接访问,需要证书,后期想办法解决证书,暂时使用http绕过
                        src = src.replace("https", "http");
                        System.out.println(src);
                        String filename = new SimpleDateFormat("yyyy-MM-dd_hh:mm:sss_").format(new Date()) + UUID.randomUUID().toString().substring(0, 4) + src.substring(src.lastIndexOf("."));
                        pics.add(new Pic(null, filename, src, title, null));
                    }
                }
                execute.close();
            }
        } catch (Exception e) {
            System.err.println("爬取帖子详情出现异常" + url);
        }

        return pics;
    }

    /*
     * 下载图片至本地
     * */
    public void Download_Pic(Pic pic, String local_path) {
        OutputStream out = null;
        try {
            HttpGet httpGet = requestFacotry.getHttpGet(pic.getHref());
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                httpGet.abort();
                System.err.println("图片链接失效：" + pic.getHref());

            }
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                File file = new File(local_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String str = local_path.substring(local_path.length() - 1);
                if ((!("/").equals(str)) && (!("\\").equals(str))) {
                    local_path = local_path + "/";
                }
                file = new File(local_path + pic.getFilename());
                out = new FileOutputStream(file);
                response.getEntity().writeTo(out);
            }
            response.close();

        } catch (IOException e) {

            System.err.println(pic.getHref());
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
