@CheckReturnValue public HeldLockSet plus(GuardedByExpression lock){
  return new HeldLockSet(locks.plus(lock));
}
