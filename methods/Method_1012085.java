@Override public StyledTextPrinter append(String text){
  try {
    this.myDocument.insertString(this.myDocument.getLength(),text,this.myAttributeSet);
  }
 catch (  BadLocationException badLocationException) {
    badLocationException.printStackTrace();
  }
  return this;
}
