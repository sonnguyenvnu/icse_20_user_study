@Override public synchronized void revoke(){
  if (disabled) {
    return;
  }
  if (activeDialog <= 0) {
    throw new RevokingStoreIllegalStateException(ACTIVE_DIALOG_POSITIVE);
  }
  disabled=true;
  try {
    RevokingState state=stack.peekLast();
    if (Objects.isNull(state)) {
      return;
    }
    state.oldValues.forEach((k,v) -> k.database.putData(k.key,v));
    state.newIds.forEach(e -> e.database.deleteData(e.key));
    state.removed.forEach((k,v) -> k.database.putData(k.key,v));
    stack.pollLast();
  }
  finally {
    disabled=false;
  }
  --activeDialog;
}
