public void relayout(){
  FontMetrics metrics=getFontMetrics();
  myHeight=(int)(metrics.getHeight() * myLineSpacing + getPaddingTop() + getPaddingBottom());
  myTextHeight=(int)(metrics.getHeight() * myLineSpacing);
  int minWidth=calculateMinWidth();
  int width=metrics.charsWidth(myText.toCharArray(),0,myText.length()) + myFontCorrectionRightGap + getPaddingLeft() + getPaddingRight();
  myWidth=Math.max(minWidth,width);
  myDescent=metrics.getDescent();
}
