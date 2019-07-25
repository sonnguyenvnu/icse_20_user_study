public synchronized void upload(AtomicBoolean cancelState){
  int created=0, obsolete=0;
  for (  OsmNoteQuest quest : questDB.getAll(null,QuestStatus.ANSWERED)) {
    if (cancelState.get())     break;
    if (uploadNoteChanges(quest) != null) {
      uploadedChangeListener.onUploaded();
      created++;
    }
 else {
      uploadedChangeListener.onDiscarded();
      obsolete++;
    }
  }
  String logMsg="Commented on " + created + " notes";
  if (obsolete > 0) {
    logMsg+=" but dropped " + obsolete + " comments because the notes have already been closed";
  }
  Log.i(TAG,logMsg);
}
