private synchronized void prune(WriteOptionsWrapper optionsWrapper){
  if (activeDialog != 0) {
    throw new RevokingStoreIllegalStateException("activeDialog has to be equal 0");
  }
  if (stack.isEmpty()) {
    throw new RevokingStoreIllegalStateException("stack is empty");
  }
  disabled=true;
  try {
    RevokingState state=stack.peekLast();
    state.oldValues.forEach((k,v) -> k.database.putData(k.key,v,optionsWrapper));
    state.newIds.forEach(e -> e.database.deleteData(e.key,optionsWrapper));
    state.removed.forEach((k,v) -> k.database.putData(k.key,v,optionsWrapper));
    stack.pollLast();
  }
  finally {
    disabled=false;
  }
}
