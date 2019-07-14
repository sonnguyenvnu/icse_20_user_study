public boolean isActive(long timeUs){
  return (startTimeUs == C.TIME_UNSET && endTimeUs == C.TIME_UNSET) || (startTimeUs <= timeUs && endTimeUs == C.TIME_UNSET) || (startTimeUs == C.TIME_UNSET && timeUs < endTimeUs) || (startTimeUs <= timeUs && timeUs < endTimeUs);
}
