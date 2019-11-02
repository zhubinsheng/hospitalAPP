package com.hjq.demo.vo;

public class UserVo {
    /*用户名称*/
    private int userId;

    /* 用户登录密码 */
    private String password;

    private String studentNo; //账号  手机号 非登录账号

    /*用户登录名称*/
    private String userName;
    private String studentSex;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
