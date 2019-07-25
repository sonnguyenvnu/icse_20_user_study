@Override public synchronized void merge(){
  if (activeDialog <= 0) {
    throw new RevokingStoreIllegalStateException(ACTIVE_DIALOG_POSITIVE);
  }
  if (activeDialog == 1 && stack.size() == 1) {
    stack.pollLast();
    --activeDialog;
    return;
  }
  if (stack.size() < 2) {
    return;
  }
  RevokingState state=stack.peekLast();
  @SuppressWarnings("unchecked") List<RevokingState> list=(List<RevokingState>)stack;
  RevokingState prevState=list.get(stack.size() - 2);
  state.oldValues.entrySet().stream().filter(e -> !prevState.newIds.contains(e.getKey())).filter(e -> !prevState.oldValues.containsKey(e.getKey())).forEach(e -> prevState.oldValues.put(e.getKey(),e.getValue()));
  prevState.newIds.addAll(state.newIds);
  state.removed.entrySet().stream().filter(e -> {
    boolean has=prevState.newIds.contains(e.getKey());
    if (has) {
      prevState.newIds.remove(e.getKey());
    }
    return !has;
  }
).filter(e -> {
    boolean has=prevState.oldValues.containsKey(e.getKey());
    if (has) {
      prevState.removed.put(e.getKey(),prevState.oldValues.get(e.getKey()));
      prevState.oldValues.remove(e.getKey());
    }
    return !has;
  }
).forEach(e -> prevState.removed.put(e.getKey(),e.getValue()));
  stack.pollLast();
  --activeDialog;
}
