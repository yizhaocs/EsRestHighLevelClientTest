import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavaMain {
    public static void main(String[] args) {
        JavaMain mJavaMain = new JavaMain();
        mJavaMain.test1();
    }

    public void test1() {
        RestHighLevelClient client = null;

        try {
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));

            indexRequest1(client);
            getRequest1(client);




        }catch (Exception e){
            System.out.println("unknow exception");
        }finally {
            try {
                if(client != null) {
                    client.close();
                }
            }catch (IOException ioe){
                System.out.println("IOException");
            }
        }
    }

    /*
        providing the json string
     */
    private void indexRequest1(RestHighLevelClient client) throws Exception{
        IndexRequest indexRequest = new IndexRequest("posts");
        indexRequest.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexRequest.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }

    /*
     providing the document source, Document source provided as a Map which gets automatically converted to JSON format
  */
    private void indexRequest2(RestHighLevelClient client) throws Exception{
        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1").source(jsonMap);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }


    /*
        providing the document source, Document source provided as Object key-pairs, which gets converted to JSON format
    */
    private void indexRequest3(RestHighLevelClient client) throws Exception{
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    }


    private void getRequest1(RestHighLevelClient client ) throws Exception{
        GetRequest getRequest = new GetRequest(
                "posts",
                "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        retrieveTheRequestedDocumentInGetResponse(getResponse);
    }

    private void retrieveTheRequestedDocumentInGetResponse(GetResponse getResponse){
        String index = getResponse.getIndex();
        String id = getResponse.getId();
        System.out.printf(" index: %s%n id: %s%n", index, id);
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
            System.out.printf(" version: %s%n sourceAsString: %s%n sourceAsMap: %s%n" , version, sourceAsString, sourceAsMap);
        } else {
            System.out.println("returned response has 404 status code");
        }
    }
}
