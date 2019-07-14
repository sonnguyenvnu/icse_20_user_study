synchronized void open(){
  boolean old=mCondition;
  mCondition=true;
  if (!old) {
    this.notify();
  }
}
