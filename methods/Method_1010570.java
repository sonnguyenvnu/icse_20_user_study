@Override public void advance(int work){
  check();
  if (myTotal < 0) {
    throw new IllegalStateException("call start() first");
  }
  assert work >= 0;
  if (myTotal < myDone + work || myDone + work < 0) {
    LOG.warn("advance(work): work is too big: total=" + myTotal + "; done=" + myDone + "; work=" + work);
    myDone=myTotal;
  }
 else {
    myDone+=work;
  }
  update();
}
