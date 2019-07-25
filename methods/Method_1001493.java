public void increment(){
  final long now=System.currentTimeMillis();
  lastMinute.incKey(now / 1000L);
  lastHour.incKey(now / (60 * 1000L));
  lastDay.incKey(now / (10 * 60 * 1000L));
}
