public boolean needConvert(){
  return !roundVideo || roundVideo && (startTime > 0 || endTime != -1 && endTime != estimatedDuration);
}
