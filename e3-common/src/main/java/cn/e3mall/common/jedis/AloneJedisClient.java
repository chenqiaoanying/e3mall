package cn.e3mall.common.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class AloneJedisClient implements JedisClient {

    private Jedis jedis;

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    @Override
    public Long del(String key) {
        return jedis.del(key);
    }

    @Override
    public Boolean exits(String key) {
        return jedis.exists(key);
    }

    @Override
    public String hget(String key, String field) {
        return jedis.hget(key, field);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedis.hset(key, field, value);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return jedis.hdel(key, fields);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return jedis.hexists(key, field);
    }

    @Override
    public List<String> hvals(String key) {
        return jedis.hvals(key);
    }

    @Override
    public Long incr(String key) {
        return jedis.incr(key);
    }

    @Override
    public Long expire(String key, Integer seconds) {
        return jedis.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedis.ttl(key);
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
