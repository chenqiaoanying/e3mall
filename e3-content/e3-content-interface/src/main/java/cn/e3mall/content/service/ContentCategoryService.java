package cn.e3mall.content.service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> selectContentCatList(Long parentId);

    E3Result insertContentCat(Long parentId, String name);

    E3Result updateContentCat(Long id, String name);

    E3Result deleteContentCat(Long id);
}
