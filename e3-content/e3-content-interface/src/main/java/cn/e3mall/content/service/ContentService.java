package cn.e3mall.content.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.Content;

import java.util.List;

public interface ContentService {

    E3Result insertContent(Content content);

    List<Content> selectContentList(Long categoryId, Integer page, Integer rows);

    E3Result updateContent(Content content);

    E3Result deleteContent(List<Long> idList);

    List<Content> selectContentListByCid(Long cid);

}
