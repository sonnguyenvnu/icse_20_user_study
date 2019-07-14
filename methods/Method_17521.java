/** 
 * Removes the entry whose next access is farthest away into the future. 
 */
private void evict(){
  data.remove(data.lastInt());
  policyStats.recordEviction();
}
