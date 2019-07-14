protected void flush(){
  if (consoleDoc.hasAppendage()) {
    consoleDoc.insertAll();
    consoleTextPane.setCaretPosition(consoleDoc.getLength());
  }
}
