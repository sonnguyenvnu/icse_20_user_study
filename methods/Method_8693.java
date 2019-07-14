public void setOverrideText(String text){
  overrideText=text;
  textView.setText(text);
  if (messageCell != null) {
    ChatMessageCell cell=messageCell;
    messageCell=null;
    showForMessageCell(cell,false);
  }
}
