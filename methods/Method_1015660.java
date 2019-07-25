@GuardedBy("lock") protected boolean decrement(long credits){
  if (credits_left - credits >= 0) {
    credits_left-=credits;
    return true;
  }
  return false;
}
