package cn.e3mall.search.dao.impl;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {
    private final SolrServer solrServer;

    @Autowired
    public SearchDaoImpl(SolrServer solrServer) {
        this.solrServer = solrServer;
    }

    public SearchResult search(SolrQuery query) throws SolrServerException {
        QueryResponse queryResponse = solrServer.query(query);
        SearchResult result = new SearchResult();
        List<SearchItem> searchItems = queryResponse.getBeans(SearchItem.class);
        result.setItemList(searchItems);
        result.setRecordCount(queryResponse.getResults().getNumFound());
        return result;
    }

}
