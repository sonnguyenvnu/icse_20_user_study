@Override public void newDataAvailable(){
  if (countDownLatch != null) {
    countDownLatch.countDown();
  }
}
