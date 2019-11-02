package com.hjq.demo.vo;

import java.util.Date;

public class HuShiVo {
    //点击某个医生，即可显示医生的坐诊时间和擅长的病种，点
    private Integer id;
    private String huid;
    private String name ;
    private String timestar ;
    private String timeend ;
    private String administrative ;
    private String introduce;

    public HuShiVo() {
    }

    public HuShiVo(Integer id, String huid, String name, String timestar, String timeend, String administrative, String introduce) {
        this.id = id;
        this.huid = huid;
        this.name = name;
        this.timestar = timestar;
        this.timeend = timeend;
        this.administrative = administrative;
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestar() {
        return timestar;
    }

    public void setTimestar(String timestar) {
        this.timestar = timestar;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHuid() {
        return huid;
    }

    public void setHuid(String huid) {
        this.huid = huid;
    }
}
