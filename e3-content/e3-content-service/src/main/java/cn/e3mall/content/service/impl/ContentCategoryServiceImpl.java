package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.ContentCategoryMapper;
import cn.e3mall.pojo.ContentCategory;
import cn.e3mall.pojo.ContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private final ContentCategoryMapper contentCategoryMapper;

    @Autowired
    public ContentCategoryServiceImpl(ContentCategoryMapper contentCategoryMapper) {
        this.contentCategoryMapper = contentCategoryMapper;
    }

    @Override
    public List<EasyUITreeNode> selectContentCatList(Long parentId) {
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for (ContentCategory category : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(category.getName());
            node.setId(category.getId());
            node.setState(category.getIsParent() ? "closed" : "open");
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public E3Result insertContentCat(Long parentId, String name) {
        ContentCategory category = new ContentCategory();
        Date date = new Date();
        category.setParentId(parentId);
        category.setName(name);
        category.setCreated(date);
        category.setUpdated(date);
        category.setIsParent(false);
        category.setStatus(1);
        category.setSortOrder(1);
        contentCategoryMapper.insert(category);
        ContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentCategory.getIsParent()) {
            parentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        return E3Result.ok(category);
    }

    @Override
    public E3Result updateContentCat(Long id, String name) {
        ContentCategory category = new ContentCategory();
        category.setUpdated(new Date());
        category.setName(name);
        category.setId(id);
        contentCategoryMapper.updateByPrimaryKeySelective(category);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteContentCat(Long id) {
        ContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        if (category.getIsParent())
            return E3Result.build(201, "尚有子目录，无法删除！");
        contentCategoryMapper.deleteByPrimaryKey(id);
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(category.getParentId());
        List<ContentCategory> subCategoryList = contentCategoryMapper.selectByExample(example);
        if (subCategoryList.isEmpty()) {
            ContentCategory parentCategory = new ContentCategory();
            parentCategory.setId(category.getParentId());
            parentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(parentCategory);
        }
        return E3Result.ok();
    }

}
