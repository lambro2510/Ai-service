package com.lambro2510.service.Utils.cache;

import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class LockManager {
  public static final String LOCK_PREFIX = "95tv_lock";

  public static final String LOCK_PREFIX_GAME = "95tv_lock_game";

  public static final int WAIT_TIME = 5; // the maximum time to aquire the lock

  public static final int TIME_LOCK_IN_SECOND = 10;

  @Autowired
  private RedissonClient client;



  public void unLock(RLock lock) {
    if (lock != null) lock.unlockAsync();
  }

  public RLock startLockUpdateStatistic() {
    RLock lock = client.getLock(LOCK_PREFIX + ":statistic");
    lock.lock(TIME_LOCK_IN_SECOND, TimeUnit.SECONDS);
    return lock;
  }

}
