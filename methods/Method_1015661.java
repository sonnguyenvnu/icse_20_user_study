public void replenish(Address sender,long new_credits){
  if (sender == null)   return;
  lock.lock();
  try {
    Long val=credits.get(sender);
    if (val == null)     return;
    boolean potential_update=val - accumulated_credits <= min_credits;
    decrementAndAdd(sender,new_credits);
    if (potential_update) {
      long new_min=computeLowestCredit();
      if (new_min > min_credits) {
        min_credits=new_min;
        credits_available.signalAll();
      }
    }
  }
  finally {
    lock.unlock();
  }
}
