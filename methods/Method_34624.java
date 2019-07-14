@Override public void setComplete(){
  if (!isTerminated()) {
    subject.onCompleted();
  }
}
