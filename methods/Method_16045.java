@Bean public AopLockAdvisor aopLockAdvisor(LockManager lockManager){
  return new AopLockAdvisor(lockManager);
}
