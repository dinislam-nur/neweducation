package DINS_Task.Platform_task;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainTestExample1 {

    public static void main(String[] args) {
        try {
            URI uri = new URIBuilder()
//                    .setScheme("http")
//                    .setHost("some_domain.com")
//                    .setPathSegments("company", "777", "Izergil")
//                    .setParameter("name", "Ivan")
//                    .build();
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("q", "httpclient")
                    .setParameter("btnG", "Google Search")
                    .setParameter("aq", "f")
                    .setParameter("oq", "")
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpClient request = HttpClients.createDefault();
            try (CloseableHttpResponse response = request.execute(httpGet)) {
                System.out.println(response.getStatusLine().getStatusCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
