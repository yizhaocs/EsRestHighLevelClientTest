import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.HashMap;
import java.util.Map;

public class SearchAPI {
     void countApi(RestHighLevelClient client) throws Exception{
        /*
            Count Request
         */
        CountRequest countRequest = new CountRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        countRequest.source(searchSourceBuilder);

        /*
            Synchronous execution
         */
        CountResponse countResponse = client
                .count(countRequest, RequestOptions.DEFAULT);

        /*
            CountResponse
         */
        readCountResponse(countResponse);

    }

    void searchTemplateRequest(RestHighLevelClient client, String script) throws Exception{
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest("posts"));

        request.setScriptType(ScriptType.INLINE);
        request.setScript(script);

        Map<String, Object> scriptParams = new HashMap();
        scriptParams.put("field", "title");
        scriptParams.put("value", "elasticsearch");
        scriptParams.put("size", 5);
        request.setScriptParams(scriptParams);

        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);

        SearchResponse searchResponse = response.getResponse();
        System.out.printf("searchResponse: %s%n", searchResponse);
    }

    void readCountResponse(CountResponse countResponse){
        long count = countResponse.getCount();
        RestStatus status = countResponse.status();
        Boolean terminatedEarly = countResponse.isTerminatedEarly();

        int totalShards = countResponse.getTotalShards();
        int skippedShards = countResponse.getSkippedShards();
        int successfulShards = countResponse.getSuccessfulShards();
        int failedShards = countResponse.getFailedShards();
        for (ShardSearchFailure failure : countResponse.getShardFailures()) {
            // failures should be handled here
        }

        System.out.printf(" count: %s%n status: %s%n terminatedEarly: %s%n" , count, status, terminatedEarly);
        System.out.printf(" totalShards: %s%n skippedShards: %s%n successfulShards: %s%n failedShards: %s%n" , totalShards, skippedShards, successfulShards, failedShards);
    }


}
