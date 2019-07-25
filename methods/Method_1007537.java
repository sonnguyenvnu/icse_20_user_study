public void trigger(final WatchdogContext doghouse){
  int maxtry=5;
  long clock=System.currentTimeMillis(), sched=0;
  int retry=-1;
  while ((retry < 0 || !timerQueue.isEmpty() || (sched > 0 && sched <= clock)) && ++retry < maxtry && lock.tryLock()) {
    try {
      sched=doTrigger(clock);
    }
  finally {
      lock.unlock();
    }
    clock=System.currentTimeMillis();
  }
  if (!doghouse.isEmptyish())   return;
  WatchdogTask dragon=argos;
  if (retry == maxtry) {
    doghouse.publish(argos=new WatchdogTask(0));
    c1++;
  }
 else   if (sched > 0 & (dragon.done | sched < dragon.time)) {
    Watcher watcher=new Watcher(doghouse,sched);
    argos=watcher.dog;
    timerProxy.schedule(watcher,sched - clock,TimeUnit.MILLISECONDS);
    c2++;
  }
}
