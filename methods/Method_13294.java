protected void initLineNumbers(){
  String text=getText();
  int len=text.length();
  if (len == 0) {
    setMaxLineNumber(0);
  }
 else {
    int mln=len - text.replace("\n","").length();
    if (text.charAt(len - 1) != '\n') {
      mln++;
    }
    setMaxLineNumber(mln);
    for (int i=1; i <= maxLineNumber; i++) {
      lineNumberMap[i]=i;
    }
  }
}
