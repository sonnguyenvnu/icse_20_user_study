/** 
 * Remove all entries.
 */
@Override public void clear(){
  long max=Math.max(1,maxMemory / segmentCount);
  for (int i=0; i < segmentCount; i++) {
    segments[i]=new Segment<>(this,max,stackMoveDistance,8);
  }
}
