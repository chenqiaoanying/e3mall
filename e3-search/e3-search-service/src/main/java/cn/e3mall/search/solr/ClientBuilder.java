package cn.e3mall.search.solr;

import org.apache.solr.client.solrj.SolrClient;

public interface ClientBuilder {
    SolrClient build();
}
