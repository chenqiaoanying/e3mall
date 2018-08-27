package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.User;

public interface RegisterService {

    int USER_NAME = 1;
    int PHONE_NUM = 2;
    int E_MAIL = 3;

    E3Result checkData(String param, int type);
    E3Result register(User user);
}
