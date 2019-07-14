public long getFinalImageLoadTimeMs(){
  if (getImageRequestEndTimeMs() == UNSET || getImageRequestStartTimeMs() == UNSET) {
    return UNSET;
  }
  return getImageRequestEndTimeMs() - getImageRequestStartTimeMs();
}
