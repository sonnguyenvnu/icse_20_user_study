/** 
 * ????????
 * @return the boolean
 * @see ScheduledExecutorService#scheduleAtFixedRate(Runnable,long,long,TimeUnit)
 * @see ScheduledExecutorService#scheduleWithFixedDelay(Runnable,long,long,TimeUnit)
 */
public synchronized ScheduledService start(){
  if (started) {
    return this;
  }
  if (scheduledExecutorService == null) {
    scheduledExecutorService=new ScheduledThreadPoolExecutor(1,new NamedThreadFactory(threadName,true));
  }
  ScheduledFuture future=null;
switch (mode) {
case MODE_FIXEDRATE:
    future=scheduledExecutorService.scheduleAtFixedRate(runnable,initialDelay,period,unit);
  break;
case MODE_FIXEDDELAY:
future=scheduledExecutorService.scheduleWithFixedDelay(runnable,initialDelay,period,unit);
break;
default :
break;
}
if (future != null) {
this.future=future;
SCHEDULED_SERVICE_MAP.put(this,System.currentTimeMillis());
started=true;
}
 else {
started=false;
}
return this;
}
