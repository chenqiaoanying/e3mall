package cn.e3mall.sso.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.User;
import cn.e3mall.sso.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TokenServiceImpl implements TokenService {

    private final JedisClient jedisClient;

    @Value("${LOGIN_TIME_OUT}")
    private int LOGIN_TIME_OUT;
    @Value("${TOKEN_KEY_PREFIX}")
    private String TOKEN_KEY_PREFIX;

    @Autowired
    public TokenServiceImpl(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    @Override
    public E3Result getUserByToken(String token) {
        String json = jedisClient.get(TOKEN_KEY_PREFIX + token);
        if (StringUtils.isEmpty(json))
            return E3Result.build(201, "用户登录已过期，请重新登录");
        jedisClient.expire(TOKEN_KEY_PREFIX + token, LOGIN_TIME_OUT);
        return E3Result.ok(JsonUtils.jsonToObject(json, User.class));
    }
}
