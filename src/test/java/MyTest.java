import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyTest {

    /**
     * 模拟浏览器发送Get请求
     */
    @Test
    public void testGet(){

        //url
        String url = "https://restapi.amap.com/v3/ip?key=0113a13c88697dcea6a445584d535837&ip=192.168.0.111";

        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //使用Get请求，创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        try {
            //发送请求，使用创建的client对象
            CloseableHttpResponse response = client.execute(httpGet);

            //通过response 相应对象获取返回结果
            String result = EntityUtils.toString(response.getEntity());

            System.out.println("访问地址后，结果内容如下：" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 模拟浏览器发送Get请求
     * 以模拟表单的方式进行请求
     * @throws Exception
     */
    @Test
    public void testPost() throws Exception{

        //url地址
        String url = "https://restapi.amap.com/v3/ip";
        //创建Httpclient 对象
        CloseableHttpClient client = HttpClients.createDefault();

        //创建HttpPost对象
        HttpPost post = new HttpPost(url);

        //给Post请求，指定请求参数，以集合的方式
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key","0113a13c88697dcea6a445584d535837"));
        params.add(new BasicNameValuePair("ip","192.168.0.111"));

        //设置HttpPost请求参数
        try {
            HttpEntity entity = new UrlEncodedFormEntity(params);
            post.setEntity(entity);
            //执行请求
            CloseableHttpResponse response = client.execute(post);
            //如果执行成功，读取数据
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //关闭资源
          client.close();
        }
    }
}
