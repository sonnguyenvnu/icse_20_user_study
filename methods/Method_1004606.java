public void next(final String pUrl){
  Delay delay;
synchronized (mDelays) {
    delay=mDelays.get(pUrl);
  }
  if (delay == null) {
    delay=new Delay(mExponentialBackoffDurationInMillis);
synchronized (mDelays) {
      mDelays.put(pUrl,delay);
    }
  }
 else {
    delay.next();
  }
}
