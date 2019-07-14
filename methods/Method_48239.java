/** 
 * Returns a byte buffer representation for the given partition id
 * @param partition
 * @return
 */
protected StaticBuffer getPartitionKey(int partition){
  return BufferUtil.getIntBuffer(partition);
}
