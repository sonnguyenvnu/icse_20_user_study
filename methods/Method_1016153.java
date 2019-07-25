public void undo(){
  if (!isSomeDragged() && historyState > 0) {
    historyState=historyState - 1;
    restoreHistoryState(history.get(historyState));
  }
}
