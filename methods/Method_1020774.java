ScheduledExecutorService pick(){
  ScheduledExecutorService[] current=pool.get();
  if (current.length == 0) {
    return REJECTING;
  }
  int idx=this.n;
  if (idx >= parallelism) {
    idx=0;
  }
  this.n=idx + 1;
  return current[idx];
}
