public void clear(){
  try {
    consoleDoc.remove(0,consoleDoc.getLength());
  }
 catch (  BadLocationException e) {
  }
}
