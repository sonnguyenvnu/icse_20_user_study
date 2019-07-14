/** 
 * Increases the capacity of this <tt>FrequencySketch</tt> instance, if necessary, to ensure that it can accurately estimate the popularity of elements given the maximum size of the cache. This operation forgets all previous counts when resizing.
 * @param maximumSize the maximum size of the cache
 */
protected void ensureCapacity(long maximumSize){
  checkArgument(maximumSize >= 0);
  int maximum=(int)Math.min(maximumSize,Integer.MAX_VALUE >>> 1);
  if ((table != null) && (table.length >= maximum)) {
    return;
  }
  table=new long[(maximum == 0) ? 1 : ceilingNextPowerOfTwo(maximum)];
  tableMask=Math.max(0,table.length - 1);
}
