private int getDownloadedLengthFromOffsetInternal(ArrayList<Range> ranges,final int offset,final int length){
  if (ranges == null || state == stateFinished || ranges.isEmpty()) {
    if (downloadedBytes == 0) {
      return length;
    }
 else {
      return Math.min(length,Math.max(downloadedBytes - offset,0));
    }
  }
 else {
    int count=ranges.size();
    Range range;
    Range minRange=null;
    int availableLength=length;
    for (int a=0; a < count; a++) {
      range=ranges.get(a);
      if (offset <= range.start && (minRange == null || range.start < minRange.start)) {
        minRange=range;
      }
      if (range.start <= offset && range.end > offset) {
        availableLength=0;
      }
    }
    if (availableLength == 0) {
      return 0;
    }
 else     if (minRange != null) {
      return Math.min(length,minRange.start - offset);
    }
 else {
      return Math.min(length,Math.max(totalBytesCount - offset,0));
    }
  }
}
