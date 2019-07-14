private void releaseGlobalXLock(){
  releaseLocks(GLOBAL_CONTEXT,Sets.newHashSet(GLOBAL_LOCK_ID));
}
