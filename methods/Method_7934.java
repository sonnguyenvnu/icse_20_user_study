private void createLayout(CharSequence text,int width){
  int maxWidth=width - AndroidUtilities.dp(30);
  textLayout=new StaticLayout(text,Theme.chat_actionTextPaint,maxWidth,Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
  textHeight=0;
  textWidth=0;
  try {
    int linesCount=textLayout.getLineCount();
    for (int a=0; a < linesCount; a++) {
      float lineWidth;
      try {
        lineWidth=textLayout.getLineWidth(a);
        if (lineWidth > maxWidth) {
          lineWidth=maxWidth;
        }
        textHeight=(int)Math.max(textHeight,Math.ceil(textLayout.getLineBottom(a)));
      }
 catch (      Exception e) {
        FileLog.e(e);
        return;
      }
      textWidth=(int)Math.max(textWidth,Math.ceil(lineWidth));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  textX=(width - textWidth) / 2;
  textY=AndroidUtilities.dp(7);
  textXLeft=(width - textLayout.getWidth()) / 2;
}
