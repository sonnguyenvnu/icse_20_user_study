private static long[][] getAdDurations(Timeline[][] adTimelines,Timeline.Period period){
  long[][] adDurations=new long[adTimelines.length][];
  for (int i=0; i < adTimelines.length; i++) {
    adDurations[i]=new long[adTimelines[i].length];
    for (int j=0; j < adTimelines[i].length; j++) {
      adDurations[i][j]=adTimelines[i][j] == null ? C.TIME_UNSET : adTimelines[i][j].getPeriod(0,period).getDurationUs();
    }
  }
  return adDurations;
}
