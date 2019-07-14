protected void handleSaveImpl(){
  statusNotice(Language.text("editor.status.saving"));
  try {
    if (sketch.save()) {
      statusNotice(Language.text("editor.status.saving.done"));
    }
 else {
      statusEmpty();
    }
  }
 catch (  Exception e) {
    statusError(e);
  }
}
