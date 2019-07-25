public void run(){
  int lastrate=-1;
  for (; ; ) {
    int change=random.nextInt(10);
    if (random.nextInt(2) == 0) {
      change=0 - change;
    }
    int rate=60000 / (time + change);
    if (rate < 120 && rate > 50) {
      time+=change;
      notifyBeatObservers();
      if (rate != lastrate) {
        lastrate=rate;
        notifyBPMObservers();
      }
    }
    try {
      Thread.sleep(time);
    }
 catch (    Exception e) {
    }
  }
}
