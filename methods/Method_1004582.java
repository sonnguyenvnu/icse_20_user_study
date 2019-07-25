public long next(){
  final long duration;
  if (mDurations == null) {
    duration=mDuration;
  }
 else {
    duration=mDurations[mIndex];
    if (mIndex < mDurations.length - 1) {
      mIndex++;
    }
  }
  mNextTime=now() + duration;
  return duration;
}
