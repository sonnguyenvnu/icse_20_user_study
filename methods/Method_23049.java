public synchronized void run(){
  while (true) {
    if (s == null) {
      try {
        wait();
      }
 catch (      InterruptedException e) {
        continue;
      }
    }
    try {
      handleClient();
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
    s=null;
    Vector pool=WebServer.threads;
synchronized (pool) {
      if (pool.size() >= WebServer.workers) {
        return;
      }
 else {
        pool.addElement(this);
      }
    }
  }
}
