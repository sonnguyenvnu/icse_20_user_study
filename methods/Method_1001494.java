public void increment(int value){
  final long now=System.currentTimeMillis();
  lastMinute.incKey(now / 1000L,value);
  lastHour.incKey(now / (60 * 1000L),value);
  lastDay.incKey(now / (10 * 60 * 1000L),value);
}
