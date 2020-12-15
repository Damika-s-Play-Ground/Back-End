package lk.ijse.dep.web.model;

import java.math.BigDecimal;

/**
 * @author : Damika Anupama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/12/2020
 **/
public class PlaceOrder {
    private String orderId;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;

    public PlaceOrder() {
    }

    public PlaceOrder(String orderId, String itemCode, int qty, BigDecimal unitPrice) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
