public void close(Duration duration){
  if (!isAlive()) {
    log.warn("Already closed: {}",this);
    return;
  }
  final long maxWaitMs=duration.toMillis();
  softInterrupted=true;
  if (interruptible)   interrupt();
  try {
    join(maxWaitMs);
  }
 catch (  InterruptedException e) {
    log.error("Interrupted while waiting for thread {} to join",e);
  }
  if (isAlive()) {
    log.error("Thread {} did not terminate in time [{}]. This could mean that important clean up functions could not be called.",getName(),maxWaitMs);
  }
}
