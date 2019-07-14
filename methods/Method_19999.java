protected void onDocumentAdded(DocumentChange change){
  mSnapshots.add(change.getNewIndex(),change.getDocument());
  notifyItemInserted(change.getNewIndex());
}
