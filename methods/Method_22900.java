public void handleAutoFormat(){
  final String source=getText();
  try {
    final String formattedText=createFormatter().format(source);
    int selectionEnd=getSelectionStop();
    if (formattedText.length() < selectionEnd - 1) {
      selectionEnd=formattedText.length() - 1;
    }
    if (formattedText.equals(source)) {
      statusNotice(Language.text("editor.status.autoformat.no_changes"));
    }
 else {
      startCompoundEdit();
      int scrollPos=textarea.getVerticalScrollPosition();
      textarea.setText(formattedText);
      setSelection(selectionEnd,selectionEnd);
      if (scrollPos != textarea.getVerticalScrollPosition()) {
        textarea.setVerticalScrollPosition(scrollPos);
      }
      stopCompoundEdit();
      sketch.setModified(true);
      statusNotice(Language.text("editor.status.autoformat.finished"));
    }
  }
 catch (  final Exception e) {
    statusError(e);
  }
}
