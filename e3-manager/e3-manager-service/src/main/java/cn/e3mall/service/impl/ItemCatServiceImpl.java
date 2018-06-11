package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.ItemCatMapper;
import cn.e3mall.pojo.ItemCat;
import cn.e3mall.pojo.ItemCatExample;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    private final ItemCatMapper itemCatMapper;

    @Autowired
    public ItemCatServiceImpl(ItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }

    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) {
        ItemCatExample example = new ItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> nodes = new ArrayList<>(itemCatList.size());
        for (ItemCat itemCat : itemCatList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(itemCat.getId());
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            node.setText(itemCat.getName());
            nodes.add(node);
        }
        return nodes;
    }
}
