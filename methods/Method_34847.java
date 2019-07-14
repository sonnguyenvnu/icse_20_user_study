/** 
 * Add value (or values) to current bucket.
 * @param value Value to be stored in current bucket such as execution latency in milliseconds
 */
public void addValue(int... value){
  if (!enabled.get())   return;
  for (  int v : value) {
    try {
      getCurrentBucket().data.addValue(v);
    }
 catch (    Exception e) {
      logger.error("Failed to add value: " + v,e);
    }
  }
}
