package cn.e3mall.common.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class AloneJedisClient implements JedisClient{

    private Jedis jedis;


    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public Long del(String key) {
        return null;
    }

    @Override
    public Boolean exits(String key) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public Long hdel(String key, String... fields) {
        return null;
    }

    @Override
    public Boolean hexits(String key, String field) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long expire(String key, Integer seconds) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
