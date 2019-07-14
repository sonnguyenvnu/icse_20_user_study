private boolean suppressRead(){
  return notifyDiscontinuity || isPendingReset();
}
