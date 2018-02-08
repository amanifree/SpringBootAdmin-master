package com.geekcattle.model.basic;

import javax.persistence.*;

public class Billno {


    @Id
    @GeneratedValue(generator = "UUID")
    private String uid;

    private String billtype;

    private Integer billmaxno;

    private String billdate;

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
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
     * @return billtype
     */
    public String getBilltype() {
        return billtype;
    }

    /**
     * @param billtype
     */
    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    /**
     * @return billmaxno
     */
    public Integer getBillmaxno() {
        return billmaxno;
    }

    /**
     * @param billmaxno
     */
    public void setBillmaxno(Integer billmaxno) {
        this.billmaxno = billmaxno;
    }
}