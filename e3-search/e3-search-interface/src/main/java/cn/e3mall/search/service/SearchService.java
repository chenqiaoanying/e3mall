package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SearchService {
    SearchResult search(String keyword, int page, int rows) throws SolrServerException, IOException;
}
