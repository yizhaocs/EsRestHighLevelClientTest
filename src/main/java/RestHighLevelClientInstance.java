import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class RestHighLevelClientInstance {
     static RestHighLevelClient client = null;
     static {
         client = new RestHighLevelClient(
                 RestClient.builder(
                         new HttpHost("localhost", 9200, "http")));
     }
     private RestHighLevelClientInstance(){

     }

    public static RestHighLevelClient getInstance() {
        return client;
    }

    public static void close() throws Exception{
         if(client != null){
             client.close();
         }
    }
}
