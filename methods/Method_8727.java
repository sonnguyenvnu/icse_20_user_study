public void setCurrentLayout(StaticLayout layout,int start,float yOffset){
  currentLayout=layout;
  currentLine=layout.getLineForOffset(start);
  lastTop=-1;
  heightOffset=yOffset;
  if (Build.VERSION.SDK_INT >= 28) {
    int lineCount=layout.getLineCount();
    if (lineCount > 0) {
      lineHeight=layout.getLineBottom(lineCount - 1) - layout.getLineTop(lineCount - 1);
    }
  }
}
