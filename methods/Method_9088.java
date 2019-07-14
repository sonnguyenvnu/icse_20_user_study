private static int clip(Interval minorRange,boolean minorWasDefined,int count){
  int size=minorRange.size();
  if (count == 0) {
    return size;
  }
  int min=minorWasDefined ? min(minorRange.min,count) : 0;
  return min(size,count - min);
}
