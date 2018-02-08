package com.geekcattle.model.console;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geekcattle.model.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import javax.persistence.*;

public class Adjust extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private String uid;

    @Column(name = "adjust_num")
    private String adjustNum;

    @Column(name = "order_unit")
    private String orderUnit;

    private String productname;

    private String factory;

    private String unit;

    private BigDecimal number;

    @Column(name = "shipment_time")
    private String shipmentTime;

    @Column(name = "adjust_begin")
    private String adjustBegin;

    @Column(name = "adjust_end")
    private String adjustEnd;

    @Column(name = "adjust_man")
    private String adjustMan;

    @Column(name = "adjust_unit")
    private String adjustUnit;

    @Column(name = "receipt_man")
    private String receiptMan;

    @Column(name = "receipt_begin")
    private String receiptBegin;

    @Column(name = "receipt_end")
    private String receiptEnd;

    @Column(name = "purcha_data_man")
    private String purchaDataMan;

    private String purchabillno;

    @Column(name = "purcha_data_begin")
    private String purchaDataBegin;

    @Column(name = "purcha_data_end")
    private String purchaDataEnd;

    @Column(name = "check_man")
    private String checkMan;

    @Column(name = "check_begin")
    private String checkBegin;

    @Column(name = "check_end")
    private String checkEnd;

    @Column(name = "distrib_pack_man")
    private String distribPackMan;

    @Column(name = "distrib_deli_man")
    private String distribDeliMan;

    @Column(name = "distrib_begin")
    private String distribBegin;

    @Column(name = "distrib_end")
    private String distribEnd;

    @Column(name = "receipt_addr")
    private String receiptAddr;

    @Column(name = "sendout_time")
    private String sendoutTime;

    @Column(name = "reciept_man")
    private String recieptMan;

    private String remark;

    private Integer state;

    private String createdat;

    private String updatedat;

    @Transient
    @JsonIgnore
    private String sort="";

    @Transient
    @JsonIgnore
    private String order="";

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return adjust_num
     */
    public String getAdjustNum() {
        return adjustNum;
    }

    /**
     * @param adjustNum
     */
    public void setAdjustNum(String adjustNum) {
        this.adjustNum = adjustNum;
    }

    /**
     * @return order_unit
     */
    public String getOrderUnit() {
        return orderUnit;
    }

    /**
     * @param orderUnit
     */
    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
    }

    /**
     * @return productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productname
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return factory
     */
    public String getFactory() {
        return factory;
    }

    /**
     * @param factory
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return number
     */
    public BigDecimal getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     * @return shipment_time
     */
    public String getShipmentTime() {
        return shipmentTime;
    }

    /**
     * @param shipmentTime
     */
    public void setShipmentTime(String shipmentTime) {
        this.shipmentTime = shipmentTime;
    }

    /**
     * @return adjust_begin
     */
    public String getAdjustBegin() {
        return adjustBegin;
    }

    /**
     * @param adjustBegin
     */
    public void setAdjustBegin(String adjustBegin) {
        this.adjustBegin = adjustBegin;
    }

    /**
     * @return adjust_end
     */
    public String getAdjustEnd() {
        return adjustEnd;
    }

    /**
     * @param adjustEnd
     */
    public void setAdjustEnd(String adjustEnd) {
        this.adjustEnd = adjustEnd;
    }

    /**
     * @return adjust_man
     */
    public String getAdjustMan() {
        return adjustMan;
    }

    /**
     * @param adjustMan
     */
    public void setAdjustMan(String adjustMan) {
        this.adjustMan = adjustMan;
    }

    /**
     * @return adjust_unit
     */
    public String getAdjustUnit() {
        return adjustUnit;
    }

    /**
     * @param adjustUnit
     */
    public void setAdjustUnit(String adjustUnit) {
        this.adjustUnit = adjustUnit;
    }

    /**
     * @return receipt_man
     */
    public String getReceiptMan() {
        return receiptMan;
    }

    /**
     * @param receiptMan
     */
    public void setReceiptMan(String receiptMan) {
        this.receiptMan = receiptMan;
    }

    /**
     * @return receipt_begin
     */
    public String getReceiptBegin() {
        return receiptBegin;
    }

    /**
     * @param receiptBegin
     */
    public void setReceiptBegin(String receiptBegin) {
        this.receiptBegin = receiptBegin;
    }

    /**
     * @return receipt_end
     */
    public String getReceiptEnd() {
        return receiptEnd;
    }

    /**
     * @param receiptEnd
     */
    public void setReceiptEnd(String receiptEnd) {
        this.receiptEnd = receiptEnd;
    }

    /**
     * @return purcha_data_man
     */
    public String getPurchaDataMan() {
        return purchaDataMan;
    }

    /**
     * @param purchaDataMan
     */
    public void setPurchaDataMan(String purchaDataMan) {
        this.purchaDataMan = purchaDataMan;
    }

    /**
     * @return purchabillno
     */
    public String getPurchabillno() {
        return purchabillno;
    }

    /**
     * @param purchabillno
     */
    public void setPurchabillno(String purchabillno) {
        this.purchabillno = purchabillno;
    }

    /**
     * @return purcha_data_begin
     */
    public String getPurchaDataBegin() {
        return purchaDataBegin;
    }

    /**
     * @param purchaDataBegin
     */
    public void setPurchaDataBegin(String purchaDataBegin) {
        this.purchaDataBegin = purchaDataBegin;
    }

    /**
     * @return purcha_data_end
     */
    public String getPurchaDataEnd() {
        return purchaDataEnd;
    }

    /**
     * @param purchaDataEnd
     */
    public void setPurchaDataEnd(String purchaDataEnd) {
        this.purchaDataEnd = purchaDataEnd;
    }

    /**
     * @return check_man
     */
    public String getCheckMan() {
        return checkMan;
    }

    /**
     * @param checkMan
     */
    public void setCheckMan(String checkMan) {
        this.checkMan = checkMan;
    }

    /**
     * @return check_begin
     */
    public String getCheckBegin() {
        return checkBegin;
    }

    /**
     * @param checkBegin
     */
    public void setCheckBegin(String checkBegin) {
        this.checkBegin = checkBegin;
    }

    /**
     * @return check_end
     */
    public String getCheckEnd() {
        return checkEnd;
    }

    /**
     * @param checkEnd
     */
    public void setCheckEnd(String checkEnd) {
        this.checkEnd = checkEnd;
    }

    /**
     * @return distrib_pack_man
     */
    public String getDistribPackMan() {
        return distribPackMan;
    }

    /**
     * @param distribPackMan
     */
    public void setDistribPackMan(String distribPackMan) {
        this.distribPackMan = distribPackMan;
    }

    /**
     * @return distrib_deli_man
     */
    public String getDistribDeliMan() {
        return distribDeliMan;
    }

    /**
     * @param distribDeliMan
     */
    public void setDistribDeliMan(String distribDeliMan) {
        this.distribDeliMan = distribDeliMan;
    }

    /**
     * @return distrib_begin
     */
    public String getDistribBegin() {
        return distribBegin;
    }

    /**
     * @param distribBegin
     */
    public void setDistribBegin(String distribBegin) {
        this.distribBegin = distribBegin;
    }

    /**
     * @return distrib_end
     */
    public String getDistribEnd() {
        return distribEnd;
    }

    /**
     * @param distribEnd
     */
    public void setDistribEnd(String distribEnd) {
        this.distribEnd = distribEnd;
    }

    /**
     * @return receipt_addr
     */
    public String getReceiptAddr() {
        return receiptAddr;
    }

    /**
     * @param receiptAddr
     */
    public void setReceiptAddr(String receiptAddr) {
        this.receiptAddr = receiptAddr;
    }

    /**
     * @return sendout_time
     */
    public String getSendoutTime() {
        return sendoutTime;
    }

    /**
     * @param sendoutTime
     */
    public void setSendoutTime(String sendoutTime) {
        this.sendoutTime = sendoutTime;
    }

    /**
     * @return reciept_man
     */
    public String getRecieptMan() {
        return recieptMan;
    }

    /**
     * @param recieptMan
     */
    public void setRecieptMan(String recieptMan) {
        this.recieptMan = recieptMan;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return createdat
     */
    public String getCreatedat() {
        return createdat;
    }

    /**
     * @param createdat
     */
    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    /**
     * @return updatedat
     */
    public String getUpdatedat() {
        return updatedat;
    }

    /**
     * @param updatedat
     */
    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getSort() {
        if(StringUtils.isEmpty(sort)){
            return "createdat";
        }else{
            return sort;
        }
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        if(StringUtils.isEmpty(order)){
            return "desc";
        }else{
        return order;}
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

