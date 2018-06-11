package cn.e3mall.controller;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCatController {

    @Resource
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.selectContentCatList(parentId);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContentCat(Long id, String name) {
        return contentCategoryService.updateContentCat(id, name);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContentCat(Long id) {
        return contentCategoryService.deleteContentCat(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCat(Long parentId, String name) {
        return contentCategoryService.insertContentCat(parentId, name);
    }

}
