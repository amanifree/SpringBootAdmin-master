package com.geekcattle.model.basic;

import com.geekcattle.model.BaseEntity;

import javax.persistence.*;

public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    private String uid;

    @Column(name = "emp_no")
    private String empNo;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "dep_id")
    private String depId;

    @Column(name = "dep_name")
    private String depName;

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
     * @return emp_no
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return emp_name
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return dep_id
     */
    public String getDepId() {
        return depId;
    }

    /**
     * @param depId
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }

    /**
     * @return dep_name
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }
}