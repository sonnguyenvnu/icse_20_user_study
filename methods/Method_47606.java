private int deleteLastTypedKey(){
  int deleted=mTypedTimes.remove(mTypedTimes.size() - 1);
  if (!isTypedTimeFullyLegal()) {
    mDoneButton.setEnabled(false);
  }
  return deleted;
}
