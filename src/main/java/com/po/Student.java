package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    private Integer sid;
    private String sname;
    private Date birthday;
    private String sex;
    private String saddress;
    private Integer classid;
    //前台需求
    private String sdate;
    private String cname;

    public Student() {
    }

    public Student(String sname, String sex, String saddress, Integer classid, String sdate) {
        this.sname = sname;
        this.sex = sex;
        this.saddress = saddress;
        this.classid = classid;
        this.sdate = sdate;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getSdate() {
        sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        return sdate;
    }

    public void setSdate(String sdate) {
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.sdate = sdate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", saddress='" + saddress + '\'' +
                ", classid=" + classid +
                ", sdate='" + sdate + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
