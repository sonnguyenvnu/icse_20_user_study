/** 
 * Drains the write buffer. 
 */
@GuardedBy("evictionLock") void drainWriteBuffer(){
  if (!buffersWrites()) {
    return;
  }
  for (int i=0; i < WRITE_BUFFER_MAX; i++) {
    Runnable task=writeBuffer().poll();
    if (task == null) {
      return;
    }
    task.run();
  }
  lazySetDrainStatus(PROCESSING_TO_REQUIRED);
}
