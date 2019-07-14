synchronized void markLayoutStarted(){
  if (mIsLayoutStarted) {
    throw new IllegalStateException("Duplicate layout of a component: " + this);
  }
  mIsLayoutStarted=true;
}
