@Override public void run(){
  try {
    while (true) {
synchronized (waiter) {
        hiding=false;
        waiter.notifyAll();
        waiter.wait();
        hiding=true;
      }
      try {
        if (delay > 0) {
          Thread.sleep(delay);
        }
        visible=false;
        skip=true;
        repaint();
      }
 catch (      InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
 catch (  InterruptedException ie) {
    ie.printStackTrace();
  }
}
