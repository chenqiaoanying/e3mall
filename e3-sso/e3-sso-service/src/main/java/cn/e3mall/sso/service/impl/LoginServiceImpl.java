package cn.e3mall.sso.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.UserMapper;
import cn.e3mall.pojo.User;
import cn.e3mall.pojo.UserExample;
import cn.e3mall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final JedisClient jedisClient;

    @Value("${LOGIN_TIME_OUT}")
    private int LOGIN_TIME_OUT;
    @Value("${TOKEN_KEY_PREFIX}")
    private String TOKEN_KEY_PREFIX;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, JedisClient jedisClient) {
        this.userMapper = userMapper;
        this.jedisClient = jedisClient;
    }

    @Override
    public E3Result login(String username, String password) {
        if (username == null || password == null
                || username.isEmpty() || password.isEmpty())
            return E3Result.build(400, "用户名或密码不能为空！");

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(example);
        if (list == null || list.isEmpty())
            return E3Result.build(400, "用户名不存在！");

        User user = list.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))
            return E3Result.build(400, "密码错误！");

        String token = UUID.randomUUID().toString();
        jedisClient.set(TOKEN_KEY_PREFIX + token, JsonUtils.objectToJson(user));
        jedisClient.expire(TOKEN_KEY_PREFIX + token, LOGIN_TIME_OUT);

        return E3Result.ok(token);
    }
}
