private void fixText(){
  int index=text.lastIndexOf("??");
  if (index == text.length() - "??".length()) {
    text=text.substring(0,index) + "??";
  }
}
