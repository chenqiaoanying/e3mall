package cn.e3mall.sso.service.impl;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.UserMapper;
import cn.e3mall.pojo.User;
import cn.e3mall.pojo.UserExample;
import cn.e3mall.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserMapper userMapper;

    @Autowired
    public RegisterServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public E3Result checkData(String param, int type) {
        UserExample userExample = new UserExample();
        switch (type) {
            case PHONE_NUM:
                userExample.createCriteria().andPhoneEqualTo(param);
                break;
            case USER_NAME:
                userExample.createCriteria().andUsernameEqualTo(param);
                break;
            case E_MAIL:
                userExample.createCriteria().andEmailEqualTo(param);
                break;
            default:
                return E3Result.build(400, "数据类型错误");
        }
        if (userMapper.countByExample(userExample) == 0)
            return E3Result.ok(true);
        else
            return E3Result.ok(false);
    }

    @Override
    public E3Result register(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()
                || user.getPhone() == null || user.getPhone().isEmpty())
            return E3Result.build(400, "注册信息不完整，注册失败！");
        if (!(boolean) checkData(user.getUsername(), USER_NAME).getData())
            return E3Result.build(400, "用户名已占用，注册失败！");
        if (!(boolean) checkData(user.getPhone(), PHONE_NUM).getData())
            return E3Result.build(400, "电话号码已占用，注册失败！");
        /*if (!(boolean) checkData(user.getEmail(), E_MAIL).getData())
            return E3Result.build(400, "邮箱已占用，注册失败！");*/

        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);

        return E3Result.ok();
    }

}
