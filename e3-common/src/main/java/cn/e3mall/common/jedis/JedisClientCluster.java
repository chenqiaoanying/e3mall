package cn.e3mall.common.jedis;

import redis.clients.jedis.JedisCluster;

import java.util.List;

public class JedisClientCluster implements JedisClient {

    private JedisCluster cluster;

    @Override
    public String get(String key) {
        return cluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return cluster.set(key, value);
    }

    @Override
    public Long del(String key) {
        return cluster.del(key);
    }

    @Override
    public Boolean exits(String key) {
        return cluster.exists(key);
    }

    @Override
    public String hget(String key, String field) {
        return cluster.hget(key, field);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return cluster.hset(key, field, value);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return cluster.hdel(key, fields);
    }

    @Override
    public Boolean hexits(String key, String field) {
        return cluster.hexists(key, field);
    }

    @Override
    public List<String> hvals(String key) {
        return cluster.hvals(key);
    }

    @Override
    public Long incr(String key) {
        return cluster.incr(key);
    }

    @Override
    public Long expire(String key, Integer seconds) {
        return cluster.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return cluster.ttl(key);
    }

    public JedisCluster getCluster() {
        return cluster;
    }

    public void setCluster(JedisCluster cluster) {
        this.cluster = cluster;
    }
}
