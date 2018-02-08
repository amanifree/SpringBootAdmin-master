package com.geekcattle.model.console;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geekcattle.model.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Receipt extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String uid;

    @Column(name = "receipt_num")
    private String receiptNum;

    @Column(name = "delivery_unit")
    private String deliveryUnit;

    @NotEmpty(message="品名不能为空")
    private String productname;

    private String factory;

    private String unit;

    private BigDecimal number;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(name = "receipt_addr")
    private String receiptAddr;

    @Column(name = "receipt_time")
    private String receiptTime;

    @Column(name = "receipt_man")
    private String receiptMan;

    @Column(name = "accep_re_man")
    private String accepReMan;

    @Column(name = "accep_re_begin")
    private String accepReBegin;

    @Column(name = "accep_re_end")
    private String accepReEnd;

    @Column(name = "accep_check_man")
    private String accepCheckMan;

    @Column(name = "accep_check_begin")
    private String accepCheckBegin;

    @Column(name = "accep_check_end")
    private String accepCheckEnd;

    @Column(name = "accep_back_man")
    private String accepBackMan;

    @Column(name = "accep_back_begin")
    private String accepBackBegin;

    @Column(name = "accep_back_end")
    private String accepBackEnd;

    @Column(name = "tally_man")
    private String tallyMan;

    @Column(name = "tally_begin")
    private String tallyBegin;

    @Column(name = "tally_end")
    private String tallyEnd;

    private String remark;

    private Integer state;

    private String createdat;

    private String updatedat;

    @Transient
    @JsonIgnore
    private String sort = "";

    @Transient
    @JsonIgnore
    private String order = "";

    public String getSort() {
        if(StringUtils.isEmpty(sort)){
            return  "createdat";
        }else{
            return sort;
        }
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        if(StringUtils.isEmpty(sort)){
          return "desc";
        }else{
        return order;
        }
    }

    public void setOrder(String order) {
        this.order = order;
    }



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
     * @return receipt_num
     */
    public String getReceiptNum() {
        return receiptNum;
    }

    /**
     * @param receiptNum
     */
    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    /**
     * @return delivery_unit
     */
    public String getDeliveryUnit() {
        return deliveryUnit;
    }

    /**
     * @param deliveryUnit
     */
    public void setDeliveryUnit(String deliveryUnit) {
        this.deliveryUnit = deliveryUnit;
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
     * @return delivery_time
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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
     * @return receipt_time
     */
    public String getReceiptTime() {
        return receiptTime;
    }

    /**
     * @param receiptTime
     */
    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
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
     * @return accep_re_man
     */
    public String getAccepReMan() {
        return accepReMan;
    }

    /**
     * @param accepReMan
     */
    public void setAccepReMan(String accepReMan) {
        this.accepReMan = accepReMan;
    }

    /**
     * @return accep_re_begin
     */
    public String getAccepReBegin() {
        return accepReBegin;
    }

    /**
     * @param accepReBegin
     */
    public void setAccepReBegin(String accepReBegin) {
        this.accepReBegin = accepReBegin;
    }

    /**
     * @return accep_re_end
     */
    public String getAccepReEnd() {
        return accepReEnd;
    }

    /**
     * @param accepReEnd
     */
    public void setAccepReEnd(String accepReEnd) {
        this.accepReEnd = accepReEnd;
    }

    /**
     * @return accep_check_man
     */
    public String getAccepCheckMan() {
        return accepCheckMan;
    }

    /**
     * @param accepCheckMan
     */
    public void setAccepCheckMan(String accepCheckMan) {
        this.accepCheckMan = accepCheckMan;
    }

    /**
     * @return accep_check_begin
     */
    public String getAccepCheckBegin() {
        return accepCheckBegin;
    }

    /**
     * @param accepCheckBegin
     */
    public void setAccepCheckBegin(String accepCheckBegin) {
        this.accepCheckBegin = accepCheckBegin;
    }

    /**
     * @return accep_check_end
     */
    public String getAccepCheckEnd() {
        return accepCheckEnd;
    }

    /**
     * @param accepCheckEnd
     */
    public void setAccepCheckEnd(String accepCheckEnd) {
        this.accepCheckEnd = accepCheckEnd;
    }

    /**
     * @return accep_back_man
     */
    public String getAccepBackMan() {
        return accepBackMan;
    }

    /**
     * @param accepBackMan
     */
    public void setAccepBackMan(String accepBackMan) {
        this.accepBackMan = accepBackMan;
    }

    /**
     * @return accep_back_begin
     */
    public String getAccepBackBegin() {
        return accepBackBegin;
    }

    /**
     * @param accepBackBegin
     */
    public void setAccepBackBegin(String accepBackBegin) {
        this.accepBackBegin = accepBackBegin;
    }

    /**
     * @return accep_back_end
     */
    public String getAccepBackEnd() {
        return accepBackEnd;
    }

    /**
     * @param accepBackEnd
     */
    public void setAccepBackEnd(String accepBackEnd) {
        this.accepBackEnd = accepBackEnd;
    }

    /**
     * @return tally_man
     */
    public String getTallyMan() {
        return tallyMan;
    }

    /**
     * @param tallyMan
     */
    public void setTallyMan(String tallyMan) {
        this.tallyMan = tallyMan;
    }

    /**
     * @return tally_begin
     */
    public String getTallyBegin() {
        return tallyBegin;
    }

    /**
     * @param tallyBegin
     */
    public void setTallyBegin(String tallyBegin) {
        this.tallyBegin = tallyBegin;
    }

    /**
     * @return tally_end
     */
    public String getTallyEnd() {
        return tallyEnd;
    }

    /**
     * @param tallyEnd
     */
    public void setTallyEnd(String tallyEnd) {
        this.tallyEnd = tallyEnd;
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
    public String  getUpdatedat() {
        return updatedat;
    }

    /**
     * @param updatedat
     */
    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }
}