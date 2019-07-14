private void doLocalNavigation(Selection selection){
  try {
    textArea.requestFocusInWindow();
    if (selection != null) {
      textArea.setSelectionStart(selection.from);
      textArea.setSelectionEnd(selection.to);
      scrollToSelection(selection.from);
    }
 else {
      textArea.setSelectionStart(0);
      textArea.setSelectionEnd(0);
    }
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
