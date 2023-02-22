package org.ecturing.plugins;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.ecturing.Plugins;
import org.ecturing.plugins.model.QRawMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component()
public class Weather implements Plugins {
    private final String URIAddress="https://wis.qq.com/weather/common?source=pc&province=湖北&city=孝感&county=孝南区&weather_type=observe";

    private final String tokenKey="";

    private final String token="";

    @Override
    public Object call() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(URIAddress);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpGet.setConfig(requestConfig);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = null;
        int statusCode = response.getStatusLine().getStatusCode();
        if (200 == statusCode) {
            try {
                result = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            return null;
        }
        httpGet.abort();
        httpClient.getConnectionManager().shutdown();
        JSONObject Rdata=JSONObject.parseObject(result);
        JSONObject data=Rdata.getJSONObject("data");
        JSONObject observe=data.getJSONObject("observe");
        return new QRawMessage("孝感",
                observe.getString("degree"),
                observe.getString("humidity"),
                observe.getInteger("wind_power")
        ).toString();
//        return data.toString();
    }

}
