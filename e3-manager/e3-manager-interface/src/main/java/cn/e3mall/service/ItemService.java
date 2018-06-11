package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.Item;

public interface ItemService {
    Item getItemByID(long id);

    EasyUIDataGridResult getItemList(int page, int rows);

    E3Result addItem(Item item, String desc);
}
