package com.geekcattle.model.console;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geekcattle.model.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import javax.persistence.*;

public class Distrib extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private String uid;

    @Column(name = "distrib_num")
    private String distribNum;

    @Column(name = "order_unit")
    private String orderUnit;

    private String productname;

    private String factory;

    private String unit;

    private BigDecimal number;

    @Column(name = "shipment_time")
    private String shipmentTime;

    @Column(name = "order_begin")
    private String orderBegin;

    @Column(name = "order_end")
    private String orderEnd;

    @Column(name = "order_man")
    private String orderMan;

    @Column(name = "picker")
    private String picker;

    @Column(name = "pick_begin")
    private String pickBegin;

    @Column(name = "pick_end")
    private String pickEnd;

    @Column(name = "data_man")
    private String dataMan;

    private String databillno;

    @Column(name = "data_begin")
    private String dataBegin;

    @Column(name = "data_end")
    private String dataEnd;

    @Column(name = "distrib_check_man")
    private String distribCheckMan;

    @Column(name = "distrib_check_begin")
    private String distribCheckBegin;

    @Column(name = "distrib_check_end")
    private String distribCheckEnd;

    @Column(name = "distrib_pack_man")
    private String distribPackMan;

    @Column(name = "distrib_pack_begin")
    private String distribPackBegin;

    @Column(name = "distrib_pack_end")
    private String distribPackEnd;

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

    @Column(name = "arrival_time")
    private String arrivalTime;

    private String remark;

    private Integer state;

    private String createdat;

    private String updatedat;

    @Transient
    private String recestartend;

    @Transient
    private String orderinfor;


    @Transient
    @JsonIgnore
    private String sort="";


    @Transient
    @JsonIgnore
    private String order="";

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
        if (StringUtils.isEmpty(sort)){
            return "desc";
        }else {
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
     * @return distrib_num
     */
    public String getDistribNum() {
        return distribNum;
    }

    /**
     * @param distribNum
     */
    public void setDistribNum(String distribNum) {
        this.distribNum = distribNum;
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
     * @return order_begin
     */
    public String getOrderBegin() {
        return orderBegin;
    }

    /**
     * @param orderBegin
     */
    public void setOrderBegin(String orderBegin) {
        this.orderBegin = orderBegin;
    }

    /**
     * @return order_end
     */
    public String getOrderEnd() {
        return orderEnd;
    }

    /**
     * @param orderEnd
     */
    public void setOrderEnd(String orderEnd) {
        this.orderEnd = orderEnd;
    }

    /**
     * @return order_man
     */
    public String getOrderMan() {
        return orderMan;
    }

    /**
     * @param orderMan
     */
    public void setOrderMan(String orderMan) {
        this.orderMan = orderMan;
    }

    /**
     * @return data_man
     */
    public String getDataMan() {
        return dataMan;
    }

    /**
     * @param dataMan
     */
    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    /**
     * @return databillno
     */
    public String getDatabillno() {
        return databillno;
    }

    /**
     * @param databillno
     */
    public void setDatabillno(String databillno) {
        this.databillno = databillno;
    }

    /**
     * @return data_begin
     */
    public String getDataBegin() {
        return dataBegin;
    }

    /**
     * @param dataBegin
     */
    public void setDataBegin(String dataBegin) {
        this.dataBegin = dataBegin;
    }

    /**
     * @return data_end
     */
    public String getDataEnd() {
        return dataEnd;
    }

    /**
     * @param dataEnd
     */
    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    /**
     * @return distrib_check_man
     */
    public String getDistribCheckMan() {
        return distribCheckMan;
    }

    /**
     * @param distribCheckMan
     */
    public void setDistribCheckMan(String distribCheckMan) {
        this.distribCheckMan = distribCheckMan;
    }

    /**
     * @return distrib_check_begin
     */
    public String getDistribCheckBegin() {
        return distribCheckBegin;
    }

    /**
     * @param distribCheckBegin
     */
    public void setDistribCheckBegin(String distribCheckBegin) {
        this.distribCheckBegin = distribCheckBegin;
    }

    /**
     * @return distrib_check_end
     */
    public String getDistribCheckEnd() {
        return distribCheckEnd;
    }

    /**
     * @param distribCheckEnd
     */
    public void setDistribCheckEnd(String distribCheckEnd) {
        this.distribCheckEnd = distribCheckEnd;
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

    public String getDistribPackBegin() {
        return distribPackBegin;
    }

    public void setDistribPackBegin(String distribPackBegin) {
        this.distribPackBegin = distribPackBegin;
    }

    public String getDistribPackEnd() {
        return distribPackEnd;
    }

    public void setDistribPackEnd(String distribPackEnd) {
        this.distribPackEnd = distribPackEnd;
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
     * @return Updatedat
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


    /**
     * @return picker
     */
    public String getPicker() {
        return picker;
    }

    /**
     *
     * @param picker
     */
    public void setPicker(String picker) {
        this.picker = picker;
    }

    /**
     *
     * @return pick_begin
     */
    public String getPickBegin() {
        return pickBegin;
    }

    /**
     *
     * @param pickBegin
     */
    public void setPickBegin(String pickBegin) {
        this.pickBegin = pickBegin;
    }

    /**
     *
     * @return pick_end
     */
    public String getPickEnd() {
        return pickEnd;
    }

    /**
     *
     * @param pickEnd
     */
    public void setPickEnd(String pickEnd) {
        this.pickEnd = pickEnd;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getRecestartend() {
        String begin = StringUtils.substring(orderBegin,11,16);
        String end = StringUtils.substring(orderEnd,11,16);
        recestartend = orderMan+"</br>"+begin+"-"+end;
        return recestartend;
    }

    public void setRecestartend(String recestartend) {
        this.recestartend = recestartend;
    }

    public String getOrderinfor() {
        String shipment = StringUtils.substring(shipmentTime,11,16);
        orderinfor = orderUnit +"</br>"+"发出时间："+shipment;
        return orderinfor;
    }

    public void setOrderinfor(String orderinfor) {
        this.orderinfor = orderinfor;
    }
}