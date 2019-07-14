private static void checkCount(Section currentRoot,Section newRoot,ChangeSetState changeSetState){
  final boolean hasNegativeCount=(currentRoot != null && currentRoot.getCount() < 0) || (newRoot != null && newRoot.getCount() < 0);
  if (!hasNegativeCount) {
    return;
  }
  final StringBuilder message=new StringBuilder();
  message.append("Changet count is below 0! ");
  message.append("Current section: ");
  if (currentRoot == null) {
    message.append("null; ");
  }
 else {
    message.append(currentRoot.getSimpleName() + " , key=" + currentRoot.getGlobalKey() + ", count=" + currentRoot.getCount() + ", childrenSize=" + currentRoot.getChildren().size() + "; ");
  }
  message.append("Next section: ");
  if (newRoot == null) {
    message.append("null; ");
  }
 else {
    message.append(newRoot.getSimpleName() + " , key=" + newRoot.getGlobalKey() + ", count=" + newRoot.getCount() + ", childrenSize=" + newRoot.getChildren().size() + "; ");
  }
  message.append("Changes: [");
  final ChangeSet changeSet=changeSetState.mChangeSet;
  for (int i=0; i < changeSet.getCount(); i++) {
    final Change change=changeSet.getChangeAt(i);
    message.append(change.getType() + " " + change.getIndex() + " " + change.getToIndex());
    message.append(", ");
  }
  message.append("]");
  throw new IllegalStateException(message.toString());
}
