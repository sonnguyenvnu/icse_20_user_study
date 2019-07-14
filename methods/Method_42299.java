private int progressBarValue(long position){
  long duration=player == null ? C.TIME_UNSET : player.getDuration();
  return duration == C.TIME_UNSET || duration == 0 ? 0 : (int)((position * PROGRESS_BAR_MAX) / duration);
}
