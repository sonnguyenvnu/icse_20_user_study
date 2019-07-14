/** 
 * Performs the post-processing work required after a write.
 * @param task the pending operation to be applied
 */
void afterWrite(Runnable task){
  if (buffersWrites()) {
    for (int i=0; i < WRITE_BUFFER_RETRIES; i++) {
      if (writeBuffer().offer(task)) {
        scheduleAfterWrite();
        return;
      }
      scheduleDrainBuffers();
    }
    try {
      performCleanUp(task);
    }
 catch (    RuntimeException e) {
      logger.log(Level.SEVERE,"Exception thrown when performing the maintenance task",e);
    }
  }
 else {
    scheduleAfterWrite();
  }
}
