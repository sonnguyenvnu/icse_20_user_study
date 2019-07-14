private void removeNotCommittedText(AttributedCharacterIterator text){
  if (prevComposeString.length() == 0) {
    return;
  }
  try {
    textArea.getDocument().remove(initialCaretPosition,prevComposeString.length());
  }
 catch (  BadLocationException e) {
    e.printStackTrace();
  }
}
