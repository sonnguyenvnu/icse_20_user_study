private long minDurationForQualityIncreaseUs(long availableDurationUs){
  boolean isAvailableDurationTooShort=availableDurationUs != C.TIME_UNSET && availableDurationUs <= minDurationForQualityIncreaseUs;
  return isAvailableDurationTooShort ? (long)(availableDurationUs * bufferedFractionToLiveEdgeForQualityIncrease) : minDurationForQualityIncreaseUs;
}
