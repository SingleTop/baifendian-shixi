package entity;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 11:23 2018/1/10
 * @Modified_By:
 */
public
class ReputationEntity {
    private String userId;
    private String userName;
    private int starGrade;
    private String reputationDetail;
    private String time;
    private int deliveryCost;
    private String shopName;

    public
    String getUserId() {
        return userId;
    }

    public
    void setUserId(String userId) {
        this.userId = userId;
    }

    public
    String getUserName() {
        return userName;
    }

    public
    void setUserName(String userName) {
        this.userName = userName;
    }

    public
    int getStarGrade() {
        return starGrade;
    }

    public
    void setStarGrade(int starGrade) {
        this.starGrade = starGrade;
    }

    public
    String getReputationDetail() {
        return reputationDetail;
    }

    public
    void setReputationDetail(String reputationDetail) {
        this.reputationDetail = reputationDetail;
    }

    public
    String getTime() {
        return time;
    }

    public
    void setTime(String time) {
        this.time = time;
    }

    public
    int getDeliveryCost() {
        return deliveryCost;
    }

    public
    void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public
    String getShopName() {
        return shopName;
    }

    public
    void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
