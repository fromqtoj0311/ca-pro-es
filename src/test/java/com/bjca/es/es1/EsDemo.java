package com.bjca.es.es1;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import java.util.Date;

public class EsDemo {
    @Test
    public void test1() throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.242.130", 9200, "http")
                ));
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("indexResponse" + indexResponse);


        GetRequest getRequest = new GetRequest(
                "posts", //索引名称
                "1");   //文档id

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("getResponse" + indexResponse);


        client.close();
    }
}
