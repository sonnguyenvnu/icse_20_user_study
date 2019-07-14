@Override public void onEvent(QuerySnapshot documentSnapshots,FirebaseFirestoreException e){
  if (e != null) {
    Log.w(TAG,"onEvent:error",e);
    onError(e);
    return;
  }
  Log.d(TAG,"onEvent:numChanges:" + documentSnapshots.getDocumentChanges().size());
  for (  DocumentChange change : documentSnapshots.getDocumentChanges()) {
switch (change.getType()) {
case ADDED:
      onDocumentAdded(change);
    break;
case MODIFIED:
  onDocumentModified(change);
break;
case REMOVED:
onDocumentRemoved(change);
break;
}
}
onDataChanged();
}
