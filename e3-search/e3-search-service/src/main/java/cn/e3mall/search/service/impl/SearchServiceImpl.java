package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchDao searchDao;

    @Autowired
    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public SearchResult search(String keyword, int page, int rows) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("keyword");
        query.setHighlight(true);
        query.setHighlightSimplePost("<em>");
        query.setHighlightSimplePost("</em>");
        query.setStart(page);
        query.setRows(rows);
        query.set("df", "itemTitle");
        query.set("hl.fl", "itemTitle");
        SearchResult result = searchDao.search(query);
        long recordCount = result.getRecordCount();
        int totalPages = Math.round((float) recordCount / rows);
        result.setTotalPages(totalPages);
        return result;
    }
}
