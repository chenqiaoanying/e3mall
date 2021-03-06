package cn.e3mall.search.mapper;

import cn.e3mall.common.pojo.SearchItem;

import java.util.List;

public interface CustomItemMapper {
    List<SearchItem> selectSearchItemList();

    SearchItem selectSearchItemByID(Long itemId);
}
