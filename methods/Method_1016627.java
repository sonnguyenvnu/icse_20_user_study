/** 
 * Put a single BatchEntry to the cache for later processing.
 * @param batchEntry the batchEntry to write to the cache.
 */
void put(final AbstractBatchEntry batchEntry){
  try {
    this.queue.put(batchEntry);
  }
 catch (  InterruptedException e) {
    throw new RuntimeException(e);
  }
  if (this.queue.size() >= this.actions) {
    this.scheduler.submit(new Runnable(){
      @Override public void run(){
        write();
      }
    }
);
  }
}
