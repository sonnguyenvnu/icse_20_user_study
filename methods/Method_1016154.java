public void redo(){
  if (!isSomeDragged() && historyState < history.size() - 1) {
    historyState=historyState + 1;
    restoreHistoryState(history.get(historyState));
  }
}
