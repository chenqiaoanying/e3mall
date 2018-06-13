package cn.e3mall.search.dao.impl;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.solr.ClientBuilder;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {

    private final ClientBuilder clientBuilder;

    @Autowired
    public SearchDaoImpl(ClientBuilder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    public SearchResult search(SolrParams params) throws IOException, SolrServerException {
        QueryResponse queryResponse = clientBuilder.build().query(params);
        SearchResult result = new SearchResult();
        List<SearchItem> searchItems = queryResponse.getBeans(SearchItem.class);
        for (SearchItem item : searchItems) {
            item.setItemTitle(queryResponse.getHighlighting().get(item.getId()).get("itemTitle").get(0));
        }
        result.setItemList(searchItems);
        result.setRecordCount(queryResponse.getResults().getNumFound());
        return result;
    }

}
