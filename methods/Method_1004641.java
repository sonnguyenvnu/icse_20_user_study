private double tps(long count){
  long t=statEndTime;
  if (t == 0) {
    t=System.currentTimeMillis();
  }
  t=t - statStartTime;
  if (t == 0) {
    return 0;
  }
 else {
    return 1000.0 * count / t;
  }
}
