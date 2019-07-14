protected void onDocumentModified(DocumentChange change){
  if (change.getOldIndex() == change.getNewIndex()) {
    mSnapshots.set(change.getOldIndex(),change.getDocument());
    notifyItemChanged(change.getOldIndex());
  }
 else {
    mSnapshots.remove(change.getOldIndex());
    mSnapshots.add(change.getNewIndex(),change.getDocument());
    notifyItemMoved(change.getOldIndex(),change.getNewIndex());
  }
}
