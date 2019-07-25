@Override public synchronized void commit(){
  if (activeDialog <= 0) {
    throw new RevokingStoreIllegalStateException(ACTIVE_DIALOG_POSITIVE);
  }
  --activeDialog;
}
