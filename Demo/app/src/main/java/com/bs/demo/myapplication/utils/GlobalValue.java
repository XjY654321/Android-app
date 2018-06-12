package com.bs.demo.myapplication.utils;


import com.bs.demo.myapplication.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class GlobalValue {
    public static String TAG ="zjw";

    private static UserInfo userInfo;

    public static UserInfo getUserInfo() {
        if (userInfo==null){
            userInfo=new UserInfo();
        }
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        GlobalValue.userInfo = userInfo;
    }


}
