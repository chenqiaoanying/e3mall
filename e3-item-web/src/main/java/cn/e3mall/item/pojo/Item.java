package cn.e3mall.item.pojo;

public class Item extends cn.e3mall.pojo.Item {

    private String[] images;

    public Item(cn.e3mall.pojo.Item item) {
        setId(item.getId());
        setSellPoint(item.getSellPoint());
        setPrice(item.getPrice());
        setImages(item.getImage());
        setTitle(item.getTitle());
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String image) {
        if (image != null && !image.isEmpty()) {
            images = image.split(",");
        }
    }


}
