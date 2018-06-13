package cn.e3mall.search.solr.impl;

import cn.e3mall.search.solr.ClientBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class AloneSolrClientBuilder implements ClientBuilder {

    private HttpSolrClient.Builder solrClientBuilder;

    public AloneSolrClientBuilder(String baseSolrUrl) {
        solrClientBuilder = new HttpSolrClient.Builder().withBaseSolrUrl(baseSolrUrl);
    }

    @Override
    public SolrClient build() {
        return solrClientBuilder.build();
    }
}
