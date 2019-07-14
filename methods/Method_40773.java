@NotNull public static String formatTime(long millis){
  long sec=millis / 1000;
  long min=sec / 60;
  sec=sec % 60;
  long hr=min / 60;
  min=min % 60;
  return hr + ":" + min + ":" + sec;
}
