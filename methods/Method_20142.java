/** 
 * Create a list of operations that define the difference between  {@link #oldStateList} and {@link #currentStateList}.
 */
private UpdateOpHelper buildDiff(UpdateOpHelper updateOpHelper){
  prepareStateForDiff();
  collectRemovals(updateOpHelper);
  boolean hasInsertions=oldStateList.size() - updateOpHelper.getNumRemovals() != currentStateList.size();
  if (hasInsertions) {
    collectInsertions(updateOpHelper);
  }
  collectMoves(updateOpHelper);
  collectChanges(updateOpHelper);
  resetOldState();
  return updateOpHelper;
}
