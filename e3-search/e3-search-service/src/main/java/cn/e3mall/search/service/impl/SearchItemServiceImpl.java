package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.CustomItemMapper;
import cn.e3mall.search.service.SearchItemService;
import cn.e3mall.search.solr.ClientBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    private final CustomItemMapper customItemMapper;
    private final ClientBuilder clientBuilder;

    @Autowired
    public SearchItemServiceImpl(CustomItemMapper customItemMapper, ClientBuilder clientBuilder) {
        this.customItemMapper = customItemMapper;
        this.clientBuilder = clientBuilder;
    }

    @Override
    public E3Result importSearchItemList() {
        try {
            List<SearchItem> list = customItemMapper.selectSearchItemList();
            SolrClient solrClient = clientBuilder.build();
            solrClient.addBeans(list);
            solrClient.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500, "导入失败");
        }
    }

    @Override
    public boolean importSearchItem(Long itemId) {
        try {
            SearchItem searchItem = customItemMapper.selectSearchItemByID(itemId);
            SolrClient solrClient = clientBuilder.build();
            solrClient.addBean(searchItem);
            solrClient.commit();
            return true;
        } catch (SolrServerException|IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
