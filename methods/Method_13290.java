@Override public void goToLineNumber(int lineNumber){
  int textAreaLineNumber=getTextAreaLineNumber(lineNumber);
  if (textAreaLineNumber > 0) {
    try {
      int start=textArea.getLineStartOffset(textAreaLineNumber - 1);
      int end=textArea.getLineEndOffset(textAreaLineNumber - 1);
      setCaretPositionAndCenter(new DocumentRange(start,end));
    }
 catch (    BadLocationException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
}
