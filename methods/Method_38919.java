private void processTextareaEndTag(){
  inTextArea=false;
  if (textAreaValue == null) {
    return;
  }
  super.text(textAreaValue);
  textAreaValue=null;
}
