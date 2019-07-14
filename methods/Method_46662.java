@Override public void acquireLocks(String contextId,Set<String> locks,LockValue lockValue) throws FastStorageException {
  if (Objects.isNull(locks) || locks.isEmpty()) {
    return;
  }
  Map<String,LockValue> lockIds=locks.stream().collect(Collectors.toMap(lock -> contextId + lock,lock -> lockValue));
  String firstLockId=contextId + new ArrayList<>(locks).get(0);
  Boolean result=redisTemplate.opsForValue().multiSetIfAbsent(lockIds);
  if (!Optional.ofNullable(result).orElse(false)) {
    LockValue hasLockValue=(LockValue)redisTemplate.opsForValue().get(firstLockId);
    if (Objects.isNull(hasLockValue)) {
      throw new FastStorageException("acquire locks fail.",FastStorageException.EX_CODE_REPEAT_LOCK);
    }
    if (Objects.isNull(lockValue.getGroupId()) || !lockValue.getGroupId().equals(hasLockValue.getGroupId())) {
      if (hasLockValue.getLockType() == DTXLocks.X_LOCK || lockValue.getLockType() != DTXLocks.S_LOCK) {
        throw new FastStorageException("acquire locks fail.",FastStorageException.EX_CODE_REPEAT_LOCK);
      }
    }
    redisTemplate.opsForValue().multiSet(lockIds);
  }
  lockIds.forEach((k,v) -> redisTemplate.expire(k,managerConfig.getDtxTime(),TimeUnit.MILLISECONDS));
}
