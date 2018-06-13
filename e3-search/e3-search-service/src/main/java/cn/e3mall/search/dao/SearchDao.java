package cn.e3mall.search.dao;

import cn.e3mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.params.SolrParams;

import java.io.IOException;

public interface SearchDao {

    SearchResult search(SolrParams params) throws SolrServerException, IOException;

}
