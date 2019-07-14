public String getHiddenAfterString(){
  CommonHiddenStreamToken t;
  StringBuilder hiddenAfterString=new StringBuilder(100);
  for (t=hiddenAfter; t != null; t=t.getHiddenAfter()) {
    hiddenAfterString.append(t.getText());
  }
  return hiddenAfterString.toString();
}
