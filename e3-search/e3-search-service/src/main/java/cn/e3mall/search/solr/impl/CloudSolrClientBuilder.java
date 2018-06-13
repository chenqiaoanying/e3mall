package cn.e3mall.search.solr.impl;

import cn.e3mall.search.solr.ClientBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

import java.util.List;
import java.util.Optional;

public class CloudSolrClientBuilder implements ClientBuilder {

    private CloudSolrClient.Builder builder;
    private String defaultCollection;

    public CloudSolrClientBuilder(List<String> zkHosts, String defaultCollection) {
        this.builder = new CloudSolrClient.Builder(zkHosts, Optional.empty());
        this.defaultCollection = defaultCollection;
    }

    @Override
    public SolrClient build() {
        CloudSolrClient cloudSolrClient = builder.build();
        cloudSolrClient.setDefaultCollection(defaultCollection);
        return cloudSolrClient;
    }
}
