private boolean canRemovePreviousInput(int committed_count){
  return (prevCommittedCount == committed_count || prevCommittedCount > committed_count);
}
