@Override public void run(){
  while (!isFinished) {
synchronized (this) {
      try {
        wait(1000);
      }
 catch (      InterruptedException ie) {
      }
    }
  }
  isClosed=true;
}
