package cn.e3mall.controller;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentService contentService;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result saveContent(Content content) {
        return contentService.insertContent(content);
    }

    @RequestMapping(path = "/query/list")
    @ResponseBody
    public List<Content> getContentList(Long categoryId, Integer page, Integer rows) {
        return contentService.selectContentList(categoryId, page, rows);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContent(Content content) {
        return contentService.updateContent(content);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContent(@RequestParam("ids") String strIds) {
        String[] idStrArray = strIds.split(",");
        List<Long> idList = new ArrayList<>(idStrArray.length);
        for (String idStr : idStrArray)
            idList.add(Long.valueOf(idStr));
        return contentService.deleteContent(idList);
    }

}
