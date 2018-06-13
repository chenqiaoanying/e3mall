package cn.e3mall.search.message;

import cn.e3mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;

public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            Long itemId = Long.valueOf(((TextMessage) message).getText());
            searchItemService.importSearchItem(itemId);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
