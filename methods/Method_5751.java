@Override public synchronized void onSpanRemoved(Cache cache,CacheSpan span){
  Region removedRegion=new Region(span.position,span.position + span.length);
  Region floorRegion=regions.floor(removedRegion);
  if (floorRegion == null) {
    Log.e(TAG,"Removed a span we were not aware of");
    return;
  }
  regions.remove(floorRegion);
  if (floorRegion.startOffset < removedRegion.startOffset) {
    Region newFloorRegion=new Region(floorRegion.startOffset,removedRegion.startOffset);
    int index=Arrays.binarySearch(chunkIndex.offsets,newFloorRegion.endOffset);
    newFloorRegion.endOffsetIndex=index < 0 ? -index - 2 : index;
    regions.add(newFloorRegion);
  }
  if (floorRegion.endOffset > removedRegion.endOffset) {
    Region newCeilingRegion=new Region(removedRegion.endOffset + 1,floorRegion.endOffset);
    newCeilingRegion.endOffsetIndex=floorRegion.endOffsetIndex;
    regions.add(newCeilingRegion);
  }
}
