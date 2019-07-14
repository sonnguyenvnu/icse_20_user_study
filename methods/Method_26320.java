/** 
 * Returns true if the lock expression corresponds to a  {@code java.util.concurrent.locks.ReadWriteLock}.
 */
private static boolean isRWLock(GuardedByExpression guard,VisitorState state){
  Type guardType=guard.type();
  if (guardType == null) {
    return false;
  }
  Symbol rwLockSymbol=state.getSymbolFromString(JUC_READ_WRITE_LOCK);
  if (rwLockSymbol == null) {
    return false;
  }
  return state.getTypes().isSubtype(guardType,rwLockSymbol.type);
}
