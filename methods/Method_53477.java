void ensureControlURISet() throws TwitterException {
synchronized (lock) {
    try {
      int waits=0;
      while (controlURI == null) {
        lock.wait(1000);
        waits++;
        if (waits > 29)         throw new TwitterException("timed out for control uri to be ready");
      }
    }
 catch (    InterruptedException e) {
    }
  }
}
