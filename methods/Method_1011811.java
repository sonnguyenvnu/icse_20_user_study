public void process(){
  assert !(myProcessed);
  if (myAdjustedEvent != null) {
    myAdjustedEvent.reverse();
  }
  mySubject.process(this);
  markProcessed();
}
