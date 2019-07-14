private int findMaxWidthAroundLine(int line){
  int width=(int)Math.ceil(textLayout.getLineWidth(line));
  int count=textLayout.getLineCount();
  for (int a=line + 1; a < count; a++) {
    int w=(int)Math.ceil(textLayout.getLineWidth(a));
    if (Math.abs(w - width) < AndroidUtilities.dp(10)) {
      width=Math.max(w,width);
    }
 else {
      break;
    }
  }
  for (int a=line - 1; a >= 0; a--) {
    int w=(int)Math.ceil(textLayout.getLineWidth(a));
    if (Math.abs(w - width) < AndroidUtilities.dp(10)) {
      width=Math.max(w,width);
    }
 else {
      break;
    }
  }
  return width;
}
