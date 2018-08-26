package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.ItemDescMapper;
import cn.e3mall.mapper.ItemMapper;
import cn.e3mall.pojo.Item;
import cn.e3mall.pojo.ItemDesc;
import cn.e3mall.pojo.ItemExample;
import cn.e3mall.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {
    private final ItemMapper itemMapper;
    private final ItemDescMapper itemDescMapper;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, ItemDescMapper itemDescMapper, JmsTemplate jmsTemplate) {
        this.itemMapper = itemMapper;
        this.itemDescMapper = itemDescMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Item getItemByID(long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Item> items = itemMapper.selectByExample(new ItemExample());
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(items);
        result.setTotal(new PageInfo<>(items).getTotal());
        return result;
    }

    @Override
    public E3Result addItem(Item item, String desc) {
        Date data = new Date();
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);//1-正常，2-下架，3-删除
        item.setCreated(data);
        item.setUpdated(data);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(data);
        itemDesc.setUpdated(data);
        itemMapper.insert(item);
        itemDescMapper.insert(itemDesc);
        jmsTemplate.send(session -> session.createTextMessage(itemId.toString()));
        return E3Result.ok();
    }

    @Override
    public ItemDesc getItemDescById(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
}
