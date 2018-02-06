package entity;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 11:23 2018/1/10
 * @Modified_By:
 */
public
class FoodEntity {
    private String foodName;
    private String classifyInfo;
    private String shopName;
    private int nomalPrice;
    private int discountPrice;
    private int saleCount;
    private int recommendTimes;

    public
    String getFoodName() {
        return foodName;
    }

    public
    void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public
    String getClassifyInfo() {
        return classifyInfo;
    }

    public
    void setClassifyInfo(String classifyInfo) {
        this.classifyInfo = classifyInfo;
    }

    public
    String getShopName() {
        return shopName;
    }

    public
    void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public
    int getNomalPrice() {
        return nomalPrice;
    }

    public
    void setNomalPrice(int nomalPrice) {
        this.nomalPrice = nomalPrice;
    }

    public
    int getDiscountPrice() {
        return discountPrice;
    }

    public
    void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public
    int getSaleCount() {
        return saleCount;
    }

    public
    void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public
    int getRecommendTimes() {
        return recommendTimes;
    }

    public
    void setRecommendTimes(int recommendTimes) {
        this.recommendTimes = recommendTimes;
    }
}
