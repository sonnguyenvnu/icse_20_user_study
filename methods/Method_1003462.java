/** 
 * Discards changes done to the note and eventually delete new attachments
 */
private void discard(){
  if (!noteTmp.getAttachmentsList().equals(note.getAttachmentsList())) {
    for (    Attachment newAttachment : noteTmp.getAttachmentsList()) {
      if (!note.getAttachmentsList().contains(newAttachment)) {
        StorageHelper.delete(mainActivity,newAttachment.getUri().getPath());
      }
    }
  }
  goBack=true;
  if (!noteTmp.equals(noteOriginal)) {
    if (noteOriginal.get_id() == null) {
      mainActivity.deleteNote(noteTmp);
      goHome();
    }
 else {
      new SaveNoteTask(this,false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,noteOriginal);
    }
    MainActivity.notifyAppWidgets(mainActivity);
  }
 else {
    goHome();
  }
}
