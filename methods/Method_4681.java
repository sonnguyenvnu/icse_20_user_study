private long getEstimatedPosition(long position,long granuleDistance,long offset){
  position+=(granuleDistance * (endPosition - startPosition) / totalGranules) - offset;
  if (position < startPosition) {
    position=startPosition;
  }
  if (position >= endPosition) {
    position=endPosition - 1;
  }
  return position;
}
