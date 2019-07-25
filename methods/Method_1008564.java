/** 
 * return stats
 */
public TranslogStats stats(){
  return new TranslogStats(totalOperations(),sizeInBytes(),0,0);
}
