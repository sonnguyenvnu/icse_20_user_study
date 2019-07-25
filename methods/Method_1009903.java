public synchronized void close(boolean close_checked_out_resources){
  if (!broken) {
    this.broken=true;
    final Collection cleanupResources=(close_checked_out_resources ? (Collection)cloneOfManaged().keySet() : (Collection)cloneOfUnused());
    if (cullTask != null)     cullTask.cancel();
    if (idleRefurbishTask != null)     idleRefurbishTask.cancel();
    for (Iterator ii=cleanupResources.iterator(); ii.hasNext(); )     addToFormerResources(ii.next());
    managed.keySet().removeAll(cleanupResources);
    unused.removeAll(cleanupResources);
    Thread resourceDestroyer=new Thread("Resource Destroyer in BasicResourcePool.close()"){
      public void run(){
        for (Iterator ii=cleanupResources.iterator(); ii.hasNext(); ) {
          try {
            Object resc=ii.next();
            destroyResource(resc,true);
          }
 catch (          Exception e) {
            if (Debug.DEBUG) {
              if (logger.isLoggable(MLevel.FINE))               logger.log(MLevel.FINE,"BasicResourcePool -- A resource couldn't be cleaned up on close()",e);
            }
          }
        }
      }
    }
;
    resourceDestroyer.start();
    for (Iterator ii=acquireWaiters.iterator(); ii.hasNext(); )     ((Thread)ii.next()).interrupt();
    for (Iterator ii=otherWaiters.iterator(); ii.hasNext(); )     ((Thread)ii.next()).interrupt();
    if (factory != null)     factory.markBroken(this);
  }
 else {
    if (logger.isLoggable(MLevel.WARNING))     logger.warning(this + " -- close() called multiple times.");
  }
}
