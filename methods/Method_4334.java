private void run(){
  try {
    while (decode()) {
    }
  }
 catch (  InterruptedException e) {
    throw new IllegalStateException(e);
  }
}
