package com.lambro2510.service.Utils.cache;


public class CacheKey {
  private static final String prefix = "95tv";

  public static String PREFIX_GAME = prefix + ":game";

  public static String getConfigKey(String key) {
    return prefix + ":config:" + key;
  }

  }
