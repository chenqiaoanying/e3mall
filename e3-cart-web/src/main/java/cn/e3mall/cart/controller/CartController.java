package cn.e3mall.cart.controller;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.Item;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;
    @Resource
    private ItemService itemService;
    @Value("${COOKIE_CART_EXPIRE}")
    private int COOKIE_CART_EXPIRE;

    @RequestMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId,
                          @RequestParam(defaultValue = "1") Integer num,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        String json = CookieUtils.getCookieValue(request, "cart", true);
        List<Item> itemList = null;
        if (!StringUtils.isEmpty(json))
            itemList = JsonUtils.jsonToList(json, Item.class);
        if (itemList == null)
            itemList = new ArrayList<>();

        boolean find = false;
        for (Item item : itemList) {
            if (itemId.equals(item.getId())) {
                item.setNum(item.getNum() + num);
                find = true;
                break;
            }
        }
        if (!find) {
            Item item = itemService.getItemByID(itemId);
            String image = item.getImage();
            if (!StringUtils.isEmpty(image))
                item.setImage(image.split(",")[0]);
            item.setNum(num);
            itemList.add(item);
            CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(itemList), COOKIE_CART_EXPIRE, true);
        }
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        List<Item> itemList = null;
        if (!StringUtils.isEmpty(json))
            itemList = JsonUtils.jsonToList(json, Item.class);
        if (itemList == null)
            itemList = new ArrayList<>();
        request.setAttribute("cartList", itemList);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        List<Item> itemList = null;
        if (!StringUtils.isEmpty(json))
            itemList = JsonUtils.jsonToList(json, Item.class);
        if (itemList == null)
            itemList = new ArrayList<>();
        for (Item item : itemList) {
            if (itemId.equals(item.getId())) {
                item.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(itemList), COOKIE_CART_EXPIRE, true);
        return E3Result.ok();
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        String json = CookieUtils.getCookieValue(request, "cart", true);
        List<Item> itemList = null;
        if (!StringUtils.isEmpty(json))
            itemList = JsonUtils.jsonToList(json, Item.class);
        if (itemList == null)
            itemList = new ArrayList<>();

        for (Item item : itemList) {
            if (itemId.equals(item.getId())) {
                itemList.remove(item);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(itemList), COOKIE_CART_EXPIRE, true);
        return "redirect:/cart/cart.html";
    }


}
