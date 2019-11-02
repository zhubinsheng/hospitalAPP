package com.hjq.demo.vo;

public class KeShiVo {
    private String kename;
    private Integer id;
    private Integer keid;
    private Integer huid;

    public KeShiVo() {
    }

    public KeShiVo(String kename, Integer id, Integer keid, Integer huid) {
        this.kename = kename;
        this.id = id;
        this.keid = keid;
        this.huid = huid;
    }

    public String getKename() {
        return kename;
    }

    public void setKename(String kename) {
        this.kename = kename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeid() {
        return keid;
    }

    public void setKeid(Integer keid) {
        this.keid = keid;
    }

    public Integer getHuid() {
        return huid;
    }

    public void setHuid(Integer huid) {
        this.huid = huid;
    }
}
