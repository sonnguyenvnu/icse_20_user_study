public synchronized void upload(AtomicBoolean cancelState){
  int created=0, obsolete=0;
  for (  CreateNote createNote : createNoteDB.getAll(null)) {
    if (cancelState.get())     break;
    if (uploadCreateNote(createNote) != null) {
      uploadedChangeListener.onUploaded();
      created++;
    }
 else {
      uploadedChangeListener.onDiscarded();
      obsolete++;
    }
  }
  String logMsg="Created " + created + " notes";
  if (obsolete > 0) {
    logMsg+=" but dropped " + obsolete + " because they were obsolete already";
  }
  Log.i(TAG,logMsg);
}
