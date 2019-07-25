/** 
 * Remember an edit session for the undo history. If the history maximum size is reached, old edit sessions will be discarded.
 * @param editSession the edit session
 */
public void remember(EditSession editSession){
  checkNotNull(editSession);
  if (editSession.size() == 0)   return;
  while (historyPointer < history.size()) {
    history.remove(historyPointer);
  }
  history.add(editSession);
  while (history.size() > MAX_HISTORY_SIZE) {
    history.remove(0);
  }
  historyPointer=history.size();
}
