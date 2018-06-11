package cn.e3mall.search.controller;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {

    @Value("${ROWS_OF_PAGE}")
    private Integer ROWS_OF_PAGE;

    @Resource
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(required = false) Integer rows,
                         Model model) throws SolrServerException, UnsupportedEncodingException {
        keyword = new String(keyword.getBytes("UTF-8"), "ISO-8859-1");
        if (rows == null)
            rows = ROWS_OF_PAGE;
        SearchResult searchResult = searchService.search(keyword, page, rows);
        model.addAttribute("query", keyword);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", searchResult.getTotalPages());
        model.addAttribute("recordCount", searchResult.getRecordCount());
        model.addAttribute("itemList", searchResult.getItemList());
        return "search";
    }


}
