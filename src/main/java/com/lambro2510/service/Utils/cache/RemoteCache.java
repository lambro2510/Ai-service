package com.lambro2510.service.Utils.cache;

import com.lambro2510.service.Utils.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class RemoteCache {
  public static final int HOUR_EXPIRE = 3600;
  private static final int CONFIG_EXPIRE = 10 * 60;

  public static final int CACHE_DURATION_DEFAULT = 3600; // 1 tieng

  public static final int TEN_MINUTE = 10;

  public static final int DEFAULT_PAGE_SIZE = 12;

  public static final int CACHE_EXPIRED_DEFAULT_A_DAY = 3600 * 24; // 1 day
  public static final int CACHE_EXPIRED_DEFAULT_A_MONTH = 3600 * 24 * 30; // 1 day

  @Autowired
  RedissonClient redisson;

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  public void put(String key, Object value, int expireTime) {
    redisTemplate.opsForValue().set(key, Objects.requireNonNull(JsonParser.toJson(value)), expireTime, TimeUnit.SECONDS);
  }

  public void putQuantityUser(String key, String value, Long expireTime) {
    if (expireTime == null) {
      redisTemplate.opsForValue().set(key, value);
    } else {
      redisTemplate.opsForValue().set(key, value, (long) expireTime, TimeUnit.SECONDS);
    }
  }

  // them vao sau danh sach
  public void rightPush(String key, String value){
    redisTemplate.opsForList().rightPush(key, value);
  }

  public void set(String key, String value, int expireTime) {
    redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
  }

//  public void set(String key, String value) {
//    redisTemplate.opsForValue().set(key, value);
//  }

  public <T> T get(String key, Class<T> tClass) {
    try {
      String value = redisTemplate.opsForValue().get(key);
      return JsonParser.entity(value, tClass);
    } catch (Exception e) {
      return null;
    }
  }

  public String get(String key) {
    try {
      return redisTemplate.opsForValue().get(key);
    } catch (Exception e) {
      return null;
    }
  }

  public void put(String key, Object object) {
    try {
      put(key, JsonParser.toJson(object), CACHE_DURATION_DEFAULT);
    } catch (Exception e) {
      log.error("========json parser: ", e);
    }
  }

  public <T> ArrayList<T> getList(String key, Class<T> tClass) {
    try {

      String value = redisTemplate.opsForValue().get(key);
      return JsonParser.arrayList(value, tClass);

    } catch (Exception e) {

      return null;
    }
  }

  public Long getSize(String key){
    return redisTemplate.opsForList().size(key);
  }

  public void trim(String key, long start, long end){
    redisTemplate.opsForList().trim(key, start, end);
  }

  public List<String> lRange(String key, long start, long end){
    return redisTemplate.opsForList().range(key,start, end);
  }

  public Boolean exists(String key) {
    return redisTemplate.hasKey(key);
  }

  public void del(String key) {
    redisTemplate.delete(key);
  }

  public Set<String> keys(String pattern) {
    return redisTemplate.keys(pattern);
  }

  //===>Config
  public String getConfig(String key) {
    try {
      String configKey = CacheKey.getConfigKey(key);

      return get(configKey);
    } catch (Exception e) {
      return null;
    }
  }

  public void saveConfig(String key, Object value) {
    try {
      String configKey = CacheKey.getConfigKey(key);

      set(configKey, JsonParser.toJson(value), CONFIG_EXPIRE);
    } catch (Exception e) {
      log.info("=================> saveconfig: " + e);
    }
  }


  public void saveConfig(String key, String value) {
    try {
      String configKey = CacheKey.getConfigKey(key);

      set(configKey, value, CONFIG_EXPIRE);
    } catch (Exception e) {
      log.info("=================> saveconfig: " + e);
    }
  }
}
