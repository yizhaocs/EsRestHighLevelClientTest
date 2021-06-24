import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = RestHighLevelClientInstance.getInstance();
        //documentApiTest1();
        //searchAPITest1();
        //searchAPITest2();
        //searchAPITest3();
        searchAPITest4();
        searchAPITest5();

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
    private static void documentApiTest1(RestHighLevelClient client) throws Exception{
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
    private static void searchAPITest1(RestHighLevelClient client) throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        mSearchAPI.countApi(client);
    }

    /**
     * search in specific index
     * @throws Exception
     */
    /*
        "searchResponse":{
           "took":0,
           "timed_out":false,
           "_shards":{
              "total":1,
              "successful":1,
              "skipped":0,
              "failed":0
           },
           "hits":{
              "total":{
                 "value":1,
                 "relation":"eq"
              },
              "max_score":1.0,
              "hits":[
                 {
                    "_index":"posts",
                    "_type":"_doc",
                    "_id":"1",
                    "_score":1.0,
                    "_source":{
                       "postDate":"2013-01-30",
                       "message":"trying out Elasticsearch",
                       "user":"kimchy"
                    }
                 }
              ]
           }
        }
     */
    private static void searchAPITest2(RestHighLevelClient client) throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        mSearchAPI.searchTemplateRequest(client, "posts", script);
    }


    /**
     * search in all indices
     * @throws Exception
     */
    private static void searchAPITest3(RestHighLevelClient client) throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        mSearchAPI.searchTemplateRequest(client, "*", script);
    }


    /**
     * fetch all fields from all indices
     * @throws Exception
     */
    /*
    "searchResponse":{
       "took":601,
       "timed_out":false,
       "_shards":{
          "total":9,
          "successful":9,
          "skipped":0,
          "failed":0
       },
       "hits":{
          "total":{
             "value":10000,
             "relation":"gte"
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
                {
                   "key":"timestamp",
                   "doc_count":27133
                },
                {
                   "key":"event",
                   "doc_count":18750
                },
                {
                   "key":"message",
                   "doc_count":14076
                },
                {
                   "key":"agent",
                   "doc_count":14074
                },
                {
                   "key":"bytes",
                   "doc_count":14074
                },
                {
                   "key":"clientip",
                   "doc_count":14074
                },
                {
                   "key":"extension",
                   "doc_count":14074
                },
                {
                   "key":"geo",
                   "doc_count":14074
                },
                {
                   "key":"host",
                   "doc_count":14074
                },
                {
                   "key":"index",
                   "doc_count":14074
                },
                {
                   "key":"ip",
                   "doc_count":14074
                },
                {
                   "key":"machine",
                   "doc_count":14074
                },
                {
                   "key":"memory",
                   "doc_count":14074
                },
                {
                   "key":"phpmemory",
                   "doc_count":14074
                },
                {
                   "key":"referer",
                   "doc_count":14074
                },
                {
                   "key":"request",
                   "doc_count":14074
                },
                {
                   "key":"response",
                   "doc_count":14074
                },
                {
                   "key":"tags",
                   "doc_count":14074
                },
                {
                   "key":"url",
                   "doc_count":14074
                },
                {
                   "key":"utc_time",
                   "doc_count":14074
                },
                {
                   "key":"AvgTicketPrice",
                   "doc_count":13059
                },
                {
                   "key":"Cancelled",
                   "doc_count":13059
                },
                {
                   "key":"Carrier",
                   "doc_count":13059
                },
                {
                   "key":"Dest",
                   "doc_count":13059
                },
                {
                   "key":"DestAirportID",
                   "doc_count":13059
                },
                {
                   "key":"DestCityName",
                   "doc_count":13059
                },
                {
                   "key":"DestCountry",
                   "doc_count":13059
                },
                {
                   "key":"DestLocation",
                   "doc_count":13059
                },
                {
                   "key":"DestRegion",
                   "doc_count":13059
                },
                {
                   "key":"DestWeather",
                   "doc_count":13059
                },
                {
                   "key":"DistanceKilometers",
                   "doc_count":13059
                },
                {
                   "key":"DistanceMiles",
                   "doc_count":13059
                },
                {
                   "key":"FlightDelay",
                   "doc_count":13059
                },
                {
                   "key":"FlightDelayMin",
                   "doc_count":13059
                },
                {
                   "key":"FlightDelayType",
                   "doc_count":13059
                },
                {
                   "key":"FlightNum",
                   "doc_count":13059
                },
                {
                   "key":"FlightTimeHour",
                   "doc_count":13059
                },
                {
                   "key":"FlightTimeMin",
                   "doc_count":13059
                },
                {
                   "key":"Origin",
                   "doc_count":13059
                },
                {
                   "key":"OriginAirportID",
                   "doc_count":13059
                },
                {
                   "key":"OriginCityName",
                   "doc_count":13059
                },
                {
                   "key":"OriginCountry",
                   "doc_count":13059
                },
                {
                   "key":"OriginLocation",
                   "doc_count":13059
                },
                {
                   "key":"OriginRegion",
                   "doc_count":13059
                },
                {
                   "key":"OriginWeather",
                   "doc_count":13059
                },
                {
                   "key":"dayOfWeek",
                   "doc_count":13059
                },
                {
                   "key":"type",
                   "doc_count":4767
                },
                {
                   "key":"user",
                   "doc_count":4676
                },
                {
                   "key":"category",
                   "doc_count":4675
                },
                {
                   "key":"currency",
                   "doc_count":4675
                },
                {
                   "key":"customer_first_name",
                   "doc_count":4675
                },
                {
                   "key":"customer_full_name",
                   "doc_count":4675
                },
                {
                   "key":"customer_gender",
                   "doc_count":4675
                },
                {
                   "key":"customer_id",
                   "doc_count":4675
                },
                {
                   "key":"customer_last_name",
                   "doc_count":4675
                },
                {
                   "key":"customer_phone",
                   "doc_count":4675
                },
                {
                   "key":"day_of_week",
                   "doc_count":4675
                },
                {
                   "key":"day_of_week_i",
                   "doc_count":4675
                },
                {
                   "key":"email",
                   "doc_count":4675
                },
                {
                   "key":"geoip",
                   "doc_count":4675
                },
                {
                   "key":"manufacturer",
                   "doc_count":4675
                },
                {
                   "key":"order_date",
                   "doc_count":4675
                },
                {
                   "key":"order_id",
                   "doc_count":4675
                },
                {
                   "key":"products",
                   "doc_count":4675
                },
                {
                   "key":"sku",
                   "doc_count":4675
                },
                {
                   "key":"taxful_total_price",
                   "doc_count":4675
                },
                {
                   "key":"taxless_total_price",
                   "doc_count":4675
                },
                {
                   "key":"total_quantity",
                   "doc_count":4675
                },
                {
                   "key":"total_unique_products",
                   "doc_count":4675
                },
                {
                   "key":"updated_at",
                   "doc_count":92
                },
                {
                   "key":"references",
                   "doc_count":77
                },
                {
                   "key":"migrationVersion",
                   "doc_count":66
                },
                {
                   "key":"visualization",
                   "doc_count":39
                },
                {
                   "key":"task",
                   "doc_count":8
                },
                {
                   "key":"ui-metric",
                   "doc_count":6
                },
                {
                   "key":"canvas-workpad-template",
                   "doc_count":5
                },
                {
                   "key":"ui-counter",
                   "doc_count":5
                },
                {
                   "key":"application_usage_daily",
                   "doc_count":3
                },
                {
                   "key":"canvas-workpad",
                   "doc_count":3
                },
                {
                   "key":"dashboard",
                   "doc_count":3
                },
                {
                   "key":"graph-workspace",
                   "doc_count":3
                },
                {
                   "key":"index-pattern",
                   "doc_count":3
                },
                {
                   "key":"map",
                   "doc_count":3
                },
                {
                   "key":"sample-data-telemetry",
                   "doc_count":3
                },
                {
                   "key":"search",
                   "doc_count":2
                },
                {
                   "key":"@timestamp",
                   "doc_count":1
                },
                {
                   "key":"apm-telemetry",
                   "doc_count":1
                },
                {
                   "key":"config",
                   "doc_count":1
                },
                {
                   "key":"core-usage-stats",
                   "doc_count":1
                },
                {
                   "key":"ecs",
                   "doc_count":1
                },
                {
                   "key":"kibana",
                   "doc_count":1
                },
                {
                   "key":"postDate",
                   "doc_count":1
                },
                {
                   "key":"space",
                   "doc_count":1
                },
                {
                   "key":"spaces-usage-stats",
                   "doc_count":1
                },
                {
                   "key":"telemetry",
                   "doc_count":1
                }
             ]
          }
       }
    }
     */
    private static void searchAPITest4() throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{\n" +
                "  \"size\": 0,\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
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
                "}\n";
        mSearchAPI.searchTemplateRequest(client, "*",script);
    }

    /**
     * fetch all fields from all indices
     * @throws Exception
     */
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
    private static void searchAPITest5() throws Exception{
        SearchAPI mSearchAPI = new SearchAPI();
        String script = "{\n" +
                "  \"size\": 0,\n" +
                "  \"query\": {\n" +
                "    \"range\": {\n" +
                "      \"phRecvTime\": {\n" +
                "        \"from\": 1616115600000,\n" +
                "        \"to\": 1622051666674,\n" +
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
        mSearchAPI.searchTemplateRequest(client, "*",script);
    }
}
