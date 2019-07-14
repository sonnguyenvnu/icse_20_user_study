private void mergeSpan(CacheSpan span){
  Region newRegion=new Region(span.position,span.position + span.length);
  Region floorRegion=regions.floor(newRegion);
  Region ceilingRegion=regions.ceiling(newRegion);
  boolean floorConnects=regionsConnect(floorRegion,newRegion);
  boolean ceilingConnects=regionsConnect(newRegion,ceilingRegion);
  if (ceilingConnects) {
    if (floorConnects) {
      floorRegion.endOffset=ceilingRegion.endOffset;
      floorRegion.endOffsetIndex=ceilingRegion.endOffsetIndex;
    }
 else {
      newRegion.endOffset=ceilingRegion.endOffset;
      newRegion.endOffsetIndex=ceilingRegion.endOffsetIndex;
      regions.add(newRegion);
    }
    regions.remove(ceilingRegion);
  }
 else   if (floorConnects) {
    floorRegion.endOffset=newRegion.endOffset;
    int index=floorRegion.endOffsetIndex;
    while (index < chunkIndex.length - 1 && (chunkIndex.offsets[index + 1] <= floorRegion.endOffset)) {
      index++;
    }
    floorRegion.endOffsetIndex=index;
  }
 else {
    int index=Arrays.binarySearch(chunkIndex.offsets,newRegion.endOffset);
    newRegion.endOffsetIndex=index < 0 ? -index - 2 : index;
    regions.add(newRegion);
  }
}
