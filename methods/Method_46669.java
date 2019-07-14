@Override public long acquireMachineId(long machineMaxSize,long timeout) throws FastStorageException {
  try {
    acquireGlobalXLock();
    stringRedisTemplate.opsForValue().setIfAbsent(REDIS_MACHINE_ID_MAP_PREFIX + "cur_id","-1");
    for (int i=0; i < machineMaxSize; i++) {
      long curId=Objects.requireNonNull(stringRedisTemplate.opsForValue().increment(REDIS_MACHINE_ID_MAP_PREFIX + "cur_id",1));
      if (curId > machineMaxSize) {
        stringRedisTemplate.opsForValue().set(REDIS_MACHINE_ID_MAP_PREFIX + "cur_id","0");
        curId=0;
      }
      if (Optional.ofNullable(stringRedisTemplate.hasKey(REDIS_MACHINE_ID_MAP_PREFIX + curId)).orElse(true)) {
        continue;
      }
      stringRedisTemplate.opsForValue().set(REDIS_MACHINE_ID_MAP_PREFIX + curId,"",timeout,TimeUnit.MILLISECONDS);
      return curId;
    }
    throw new FastStorageException("non can used machine id",FastStorageException.EX_CODE_NON_MACHINE_ID);
  }
  finally {
    releaseGlobalXLock();
  }
}
