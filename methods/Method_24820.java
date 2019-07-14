private synchronized void replaceTextAreaCode(String code){
  int scrollLine=textArea.getVerticalScrollPosition();
  int scrollHor=textArea.getHorizontalScrollPosition();
  textArea.setText(code);
  textArea.setOrigin(scrollLine,-scrollHor);
}
