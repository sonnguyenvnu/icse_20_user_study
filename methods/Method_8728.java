@Override public void addRect(float left,float top,float right,float bottom,Direction dir){
  top+=heightOffset;
  bottom+=heightOffset;
  if (lastTop == -1) {
    lastTop=top;
  }
 else   if (lastTop != top) {
    lastTop=top;
    currentLine++;
  }
  float lineRight=currentLayout.getLineRight(currentLine);
  float lineLeft=currentLayout.getLineLeft(currentLine);
  if (left >= lineRight || left <= lineLeft && right <= lineLeft) {
    return;
  }
  if (right > lineRight) {
    right=lineRight;
  }
  if (left < lineLeft) {
    left=lineLeft;
  }
  float y=top;
  float y2;
  if (Build.VERSION.SDK_INT >= 28) {
    y2=bottom;
    if (bottom - top > lineHeight) {
      y2=heightOffset + (bottom != currentLayout.getHeight() ? (currentLayout.getLineBottom(currentLine) - currentLayout.getSpacingAdd()) : 0);
    }
  }
 else {
    y2=bottom - (bottom != currentLayout.getHeight() ? currentLayout.getSpacingAdd() : 0);
  }
  if (baselineShift < 0) {
    y2+=baselineShift;
  }
 else   if (baselineShift > 0) {
    y+=baselineShift;
  }
  if (useRoundRect) {
    if (rect == null) {
      rect=new RectF();
    }
    rect.set(left - AndroidUtilities.dp(4),y,right + AndroidUtilities.dp(4),y2);
    super.addRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),dir);
  }
 else {
    super.addRect(left,y,right,y2,dir);
  }
}
