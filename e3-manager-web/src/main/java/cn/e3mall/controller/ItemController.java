package cn.e3mall.controller;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.Item;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class ItemController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/item/{itemID}")
    @ResponseBody
    public Item getItemByID(@PathVariable("itemID") Long id) {
        return itemService.getItemByID(id);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {
        return itemService.getItemList(page, rows);
    }

    @RequestMapping(path = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(Item item, @RequestParam("desc") String desc) {
        return itemService.addItem(item, desc);
    }

}
