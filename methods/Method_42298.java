private String stringForTime(long timeMs){
  if (timeMs == C.TIME_UNSET) {
    timeMs=0;
  }
  long totalSeconds=(timeMs + 500) / 1000;
  long seconds=totalSeconds % 60;
  long minutes=(totalSeconds / 60) % 60;
  long hours=totalSeconds / 3600;
  formatBuilder.setLength(0);
  return hours > 0 ? formatter.format("%d:%02d:%02d",hours,minutes,seconds).toString() : formatter.format("%02d:%02d",minutes,seconds).toString();
}
