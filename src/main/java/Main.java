import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class Main {
    static RestHighLevelClient client = null;
    static {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    public static void main(String[] args) throws Exception {

        //documentApitest1();
        //searchAPITest1();
        //searchAPITest2();
        searchAPITest3();

        try {
            if(client != null) {
                client.close();
            }
        }catch (IOException ioe){
            System.out.println("IOException");
        }
    }

    /*
         index: posts
         id: 1
         version: 12
         sourceAsString: {"user":"kimchy","postDate":"2013-01-30","message":"trying out Elasticsearch"}
         sourceAsMap: {postDate=2013-01-30, message=trying out Elasticsearch, user=kimchy}
     */
    private static void documentApitest1() throws Exception{
        DocumentAPI mDcoumentAPI = new DocumentAPI();
        mDcoumentAPI.indexRequest1(client);
        mDcoumentAPI.getRequest1(client);
    }

    /*
         count: 1048
         status: OK
         terminatedEarly: null
         totalShards: 11
         skippedShards: 0
         successfulShards: 11
         failedShards: 0
     */
    private static void searchAPITest1() throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        mSearchAPI.countApi(client);
    }

    /*
        "searchResponse":{
           "took":1,
           "timed_out":false,
           "_shards":{
              "total":1,
              "successful":1,
              "skipped":0,
              "failed":0
           },
           "hits":{
              "total":{
                 "value":0,
                 "relation":"eq"
              },
              "max_score":null,
              "hits":[

              ]
           }
        }
     */
    private static void searchAPITest2() throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{" +
                "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                "  \"size\" : \"{{size}}\"" +
                "}";
        mSearchAPI.searchTemplateRequest(client, script);
    }

    /*
        "searchResponse":{
           "took":75,
           "timed_out":false,
           "_shards":{
              "total":1,
              "successful":1,
              "skipped":0,
              "failed":0
           },
           "hits":{
              "total":{
                 "value":0,
                 "relation":"eq"
              },
              "max_score":null,
              "hits":[

              ]
           },
           "aggregations":{
              "sterms#fields":{
                 "doc_count_error_upper_bound":0,
                 "sum_other_doc_count":0,
                 "buckets":[

                 ]
              }
           }
        }
     */
    private static void searchAPITest3() throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{\n" +
                "  \"size\": 0,\n" +
                "  \"query\": {\n" +
                "    \"range\": {\n" +
                "      \"phRecvTime\": {\n" +
                "        \"from\": 1616115600000,\n" +
                "        \"to\": 1616119200000,\n" +
                "        \"include_lower\": true,\n" +
                "        \"include_upper\": true,\n" +
                "        \"boost\": 1\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "    \"fields\": {\n" +
                "      \"terms\": {\n" +
                "        \"size\": 100,\n" +
                "        \"script\": {\n" +
                "          \"source\": \"params._source.keySet()\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        mSearchAPI.searchTemplateRequest(client, script);
    }
}
