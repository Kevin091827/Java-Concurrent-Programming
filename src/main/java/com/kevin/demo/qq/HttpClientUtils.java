package com.kevin.demo.qq;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:    HttpClient工具类，请求第三方接口
 * @Author:         Kevin
 * @CreateDate:     2019/7/9 22:33
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/9 22:33
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class HttpClientUtils {

    /**
     * <h>处理get请求</h>
     * @param params
     * @param url
     * @return
     */
    public static String doGet(Map<String,String> params, String url){

        //构造一个可自动关闭的 httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //封装响应
        CloseableHttpResponse response = null;
        //响应结果
        String result = "";
        try {
            //构造uri
            URIBuilder uriBuilder = new URIBuilder(url);
            //如果有参数
            if(params != null) {
                //拼接参数
                for (String key : params.keySet()) {
                    uriBuilder.addParameter(key, params.get(key));
                }
            }
            //完成uri构造
            URI uri = uriBuilder.build();
            log.info("request uri is: " + uri.toString());
            //构造get请求
            HttpGet httpGet = new HttpGet(uri);
            //执行请求
            response = httpClient.execute(httpGet);
            //请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //判断是否请求成功
            if(statusCode == 200){
                //请求正常
                log.info("request is ok");
                //请求结果,获取响应
                result = EntityUtils.toString(response.getEntity(),"utf-8");
                log.info("response is :"+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            if(response != null){
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * <h>无参get请求</h>
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(null,url);
    }

    /**
     * <h>普通post请求</h>
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url,Map<String,String> params){

        //构建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //封装响应
        CloseableHttpResponse response = null;
        //响应结果
        String result = "";
        //用于拼接参数
        List<NameValuePair> paramsList = new ArrayList<>();
        try {
            //构造post请求
            HttpPost httpPost = new HttpPost(url);
            //如果有参数
            if(params != null) {
                //拼接参数
                for (String key : params.keySet()) {
                    paramsList.add(new BasicNameValuePair(key, params.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramsList);
                //请求参数放于请求体中
                httpPost.setEntity(urlEncodedFormEntity);
            }
            //执行请求
            response =  httpClient.execute(httpPost);
            //获取请求状态
            int requestCode = response.getStatusLine().getStatusCode();
            //判断状态
            if(requestCode == 200){
                result = EntityUtils.toString(response.getEntity(),"utf-8");
                log.info("response is "+result);
            }
        }catch (Exception e){
            log.error("request error is :"+e.getMessage());
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * <h>处理无参普通post请求</h>
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * <h>json/xml方式进行post请求</h>
     *
     * <li>json: type = "application/json"</li>
     * <li>xml: type = "text/xml"</li>
     * @param json
     * @param url
     * @return
     */
    public static String doPost(String json,String url,String type){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            HttpPost httpPost = new HttpPost(url);
            //json方式交互
            httpPost.setHeader("Content-Type",type);
            httpPost.setHeader("charset", "utf-8");
            if(json != null){
                StringEntity stringEntity = new StringEntity(json,"utf-8");
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                log.info("request is ok");
                result = EntityUtils.toString(response.getEntity(),"utf-8");
            }
        }catch (Exception e){
            log.error("request is error: "+ e.getMessage());
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
