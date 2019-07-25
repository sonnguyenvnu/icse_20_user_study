public void update(Range.Time range,DLNAResource r){
  if (range.isStartOffsetAvailable() && range.getStartOrZero() > 0.0) {
    long now=System.currentTimeMillis();
    if (r.getMedia() != null) {
      stop(now + getTimeOffset() - (long)(range.getStart() * 1000),(long)(r.getMedia().getDuration() * 1000));
    }
 else {
      stop(now + getTimeOffset() - (long)(range.getStart() * 1000),0);
    }
  }
}
