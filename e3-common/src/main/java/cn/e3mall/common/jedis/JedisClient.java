package cn.e3mall.common.jedis;

import java.util.List;

public interface JedisClient {
    String get(String key);

    String set(String key, String value);

    Long del(String key);

    Boolean exits(String key);

    String hget(String key, String field);

    Long hset(String key, String field, String value);

    Long hdel(String key, String... fields);

    Boolean hexists(String key, String field);

    List<String> hvals(String key);

    Long incr(String key);

    Long expire(String key, Integer seconds);

    Long ttl(String key);


}
