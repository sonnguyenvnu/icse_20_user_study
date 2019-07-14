public void goToLineNumber(int lineNumber){
  try {
    textArea.setCaretPosition(textArea.getLineStartOffset(lineNumber - 1));
  }
 catch (  BadLocationException e) {
    assert ExceptionUtil.printStackTrace(e);
  }
}
