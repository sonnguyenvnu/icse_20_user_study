private long positionValue(int progress){
  long duration=player == null ? C.TIME_UNSET : player.getDuration();
  return duration == C.TIME_UNSET ? 0 : ((duration * progress) / PROGRESS_BAR_MAX);
}
