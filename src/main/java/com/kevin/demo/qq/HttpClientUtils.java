package com.kevin.demo.qq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
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

    //状态码
    private static int STATUS_OK = 200;
    //连接超时时间
    private static int CONNECTION_REQUEST_TIMEOUT = 5000;
    //连接超时时间
    private static int SOCKET_TIMEOUT = 5000;


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
            if(statusCode == STATUS_OK){
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
            if(requestCode == STATUS_OK){
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
     * @param paramString
     * @param url
     * @return
     */
    public static String doPost(String paramString,String url,String type){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            HttpPost httpPost = new HttpPost(url);
            //json方式交互
            httpPost.setHeader("Content-Type",type);
            httpPost.setHeader("charset", "utf-8");
            if(paramString != null){
                StringEntity stringEntity = new StringEntity(paramString,"utf-8");
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == STATUS_OK){
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

    /**
     * <h>post方式上传文件到第三方接口</h>
     * @param filePath
     * @param fileName
     * @param url
     */
    public static String doPostFile(String filePath,String fileName,Map<String,String> params,String url){

        //构建 httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //响应
        CloseableHttpResponse response = null;
        //响应结果
        String result = "";
        try{
            //构建 httpPost
            HttpPost httpPost = new HttpPost(url);
            //配置请求设置
            //防止文件过大，导致请求超时
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpPost.setConfig(requestConfig);
            //文件上传
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            //解决中文文件名乱码
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //解决编码问题
            multipartEntityBuilder.setCharset(Consts.UTF_8);
            ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),Consts.UTF_8);
            //可拼接参数
            if(params != null){
                for(String key : params.keySet()){
                    multipartEntityBuilder.addTextBody(key,params.get(key),contentType);
                }
            }
            if(filePath != null && fileName != null){
                //将文件路径转为文件输入流
                FileInputStream fileInputStream = new FileInputStream(filePath);
                multipartEntityBuilder.addBinaryBody("file", fileInputStream, ContentType.DEFAULT_BINARY, fileName);
            }
            //封装请求体
            httpPost.setEntity(multipartEntityBuilder.build());
            //执行请求
            response = httpClient.execute(httpPost);
            //状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //封装结果map
            Map map = new HashMap<String,String>();
            if(statusCode == STATUS_OK){
                map.put("code",statusCode);
                map.put("msg","ok");
            }else{
                map.put("code",statusCode);
                map.put("msg","error");
            }
            //封装结果json
            JSONObject resultJson = JSONObject.parseObject(map.toString());
            result = resultJson.toJSONString();
        }catch (Exception e){
            log.error("upload file request error: " + e.getMessage());
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
