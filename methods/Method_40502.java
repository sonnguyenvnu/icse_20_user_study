public void intervalReport(){
  if (count % reportInterval == 0) {
    long endTime=System.currentTimeMillis();
    long totalTime=endTime - startTime;
    long seconds=totalTime / 1000;
    if (seconds == 0) {
      seconds=1;
    }
    long rate=count / seconds;
    Util.msg("\n" + count + " items processed" + ", time: " + Util.timeString(totalTime) + ", rate: " + count / seconds);
    if (total > 0) {
      long rest=total - count;
      long eta=rest / rate;
      Util.msg("ETA: " + Util.timeString(eta * 1000));
    }
  }
}
