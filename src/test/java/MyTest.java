import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

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
            CloseableHttpResponse closeableHttpResponse = client.execute(httpGet);
            //通过response 相应对象获取返回结果
            String result = EntityUtils.toString(closeableHttpResponse.getEntity());
            System.out.println("访问地址后，结果内容如下：" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
