package entity;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 13:54 2017/12/21
 * @Modified_By:
 */
public
class ShopEntity {
   private int shopId;
   private String shopName = null;
   private String shopUrl = null;
   private String shopProvince = null;
   private String shopCity = null;
   private String shopDetailLocation = null;
   private String discountDetail = null;
   private int commentNum = 0;
   private int shopStar = 0;
   private int shopHoleScore = 0;
   private int deliveryScore = 0;
   private int deliveryFee = 0;
   private int deliveryThreshold = 0;
   private int deliveryAverWaiting = 0;


    @Override
    public
    String toString() {
        return "ShopEntity{" + "shopId=" + shopId + ", shopName='" + shopName + '\'' + ", shopUrl='" + shopUrl + '\'' + ", shopProvince='" + shopProvince + '\'' + ", shopCity='" + shopCity + '\'' + ", shopDetailLocation='" + shopDetailLocation + '\'' + ", discountDetail='" + discountDetail + '\'' + ", commentNum=" + commentNum + ", shopStar=" + shopStar + ", shopHoleScore=" + shopHoleScore + ", deliveryScore=" + deliveryScore + ", deliveryFee=" + deliveryFee + ", deliveryThreshold=" + deliveryThreshold + ", deliveryAverWaiting=" + deliveryAverWaiting + '}';
    }

    public
    int getShopId() {
        return shopId;
    }

    public
    void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public
    String getShopDetailLocation() {
        return shopDetailLocation;
    }

    public
    void setShopDetailLocation(String shopDetailLocation) {
        this.shopDetailLocation = shopDetailLocation;
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
    String getShopUrl() {
        return shopUrl;
    }

    public
    void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public
    String getShopProvince() {
        return shopProvince;
    }

    public
    void setShopProvince(String shopProvince) {
        this.shopProvince = shopProvince;
    }

    public
    String getShopCity() {
        return shopCity;
    }

    public
    void setShopCity(String shopCity) {
        this.shopCity = shopCity;
    }


    public
    String getDiscountDetail() {
        return discountDetail;
    }

    public
    void setDiscountDetail(String discountDetail) {
        this.discountDetail = discountDetail;
    }

    public
    int getCommentNum() {
        return commentNum;
    }

    public
    void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public
    int getShopStar() {
        return shopStar;
    }

    public
    void setShopStar(int shopStar) {
        this.shopStar = shopStar;
    }

    public
    int getShopHoleScore() {
        return shopHoleScore;
    }

    public
    void setShopHoleScore(int shopHoleScore) {
        this.shopHoleScore = shopHoleScore;
    }


    public
    int getDeliveryScore() {
        return deliveryScore;
    }

    public
    void setDeliveryScore(int deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public
    int getDeliveryFee() {
        return deliveryFee;
    }

    public
    void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public
    int getDeliveryThreshold() {
        return deliveryThreshold;
    }

    public
    void setDeliveryThreshold(int deliveryThreshold) {
        this.deliveryThreshold = deliveryThreshold;
    }

    public
    int getDeliveryAverWaiting() {
        return deliveryAverWaiting;
    }

    public
    void setDeliveryAverWaiting(int deliveryAverWaiting) {
        this.deliveryAverWaiting = deliveryAverWaiting;
    }
}
