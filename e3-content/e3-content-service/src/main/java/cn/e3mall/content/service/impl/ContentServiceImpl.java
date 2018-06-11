package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.ContentMapper;
import cn.e3mall.pojo.Content;
import cn.e3mall.pojo.ContentExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;
    private final JedisClient jedisClient;

    @Autowired
    public ContentServiceImpl(ContentMapper contentMapper, JedisClient jedisClient) {
        this.contentMapper = contentMapper;
        this.jedisClient = jedisClient;
    }


    @Override
    public E3Result insertContent(Content content) {
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        contentMapper.insert(content);
        return E3Result.ok();
    }

    @Override
    public List<Content> selectContentList(Long categoryId, Integer page, Integer rows) {
        ContentExample example = new ContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page, rows);
        return contentMapper.selectByExample(example);
    }

    @Override
    public E3Result updateContent(Content content) {
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(content);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(List<Long> idList) {
        ContentExample example = new ContentExample();
        example.createCriteria().andIdIn(idList);
        contentMapper.deleteByExample(example);
        return E3Result.ok();
    }

    @Override
    public List<Content> selectContentListByCid(Long cid) {
        ContentExample example = new ContentExample();
        example.createCriteria().andCategoryIdEqualTo(cid);
        return contentMapper.selectByExampleWithBLOBs(example);
    }

}
