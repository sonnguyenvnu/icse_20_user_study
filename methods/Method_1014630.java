void sample(long intervalNS){
  long prev=System.nanoTime();
  long end=prev + intervalNS;
  long now;
  do {
    now=System.nanoTime();
    long time=now - prev;
    if (time >= DELAY[0]) {
      int i;
      for (i=1; i < DELAY.length; i++)       if (time < DELAY[i])       break;
      count[i - 1]++;
    }
    prev=now;
  }
 while (now < end);
  totalTime+=intervalNS;
}
