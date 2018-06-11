package cn.e3mall.portal.controller;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.Content;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Value("${CONTENT_ID}")
    private Long CONTENT_ID;

    @Resource
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<Content> contentList = contentService.selectContentListByCid(CONTENT_ID);
        model.addAttribute("adList", contentList);
        return "index";
    }
}
