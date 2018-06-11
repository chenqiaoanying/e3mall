package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.CustomItemMapper;
import cn.e3mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    private final CustomItemMapper customItemMapper;
    private final SolrServer solrServer;


    @Autowired
    public SearchItemServiceImpl(CustomItemMapper customItemMapper, SolrServer solrServer) {
        this.customItemMapper = customItemMapper;
        this.solrServer = solrServer;
    }

    @Override
    public E3Result selectSearchItemList() {
        try {
            List<SearchItem> list = customItemMapper.selectItemList();
            List<SolrInputDocument> documentList = new ArrayList<>();
            for (SearchItem searchItem : list) {
                SolrInputDocument solrDocument = new SolrInputDocument();
                solrDocument.addField("id", searchItem.getId());
                solrDocument.addField("itemCategoryName", searchItem.getCategoryName());
                solrDocument.addField("itemTitle", searchItem.getTitle());
                solrDocument.addField("itemSellPoint", searchItem.getSellPoint());
                solrDocument.addField("itemPrice", searchItem.getPrice());
                solrDocument.addField("itemImage", searchItem.getImage());
                documentList.add(solrDocument);
            }
            solrServer.add(documentList);
            solrServer.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500, "导入失败");
        }
    }
}
