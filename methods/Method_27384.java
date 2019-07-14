private BetterLinkMovementExtended.ClickableSpanWithText findClickableSpanUnderTouch(TextView textView,Spannable text,MotionEvent event){
  int touchX=(int)event.getX();
  int touchY=(int)event.getY();
  touchX-=textView.getTotalPaddingLeft();
  touchY-=textView.getTotalPaddingTop();
  touchX+=textView.getScrollX();
  touchY+=textView.getScrollY();
  Layout layout=textView.getLayout();
  int touchedLine=layout.getLineForVertical(touchY);
  int touchOffset=layout.getOffsetForHorizontal(touchedLine,(float)touchX);
  this.touchedLineBounds.left=layout.getLineLeft(touchedLine);
  this.touchedLineBounds.top=(float)layout.getLineTop(touchedLine);
  this.touchedLineBounds.right=layout.getLineWidth(touchedLine) + this.touchedLineBounds.left;
  this.touchedLineBounds.bottom=(float)layout.getLineBottom(touchedLine);
  if (this.touchedLineBounds.contains((float)touchX,(float)touchY)) {
    Object[] spans=text.getSpans(touchOffset,touchOffset,SPAN_CLASS);
    for (    Object span : spans) {
      if (span instanceof ClickableSpan) {
        return ClickableSpanWithText.ofSpan(textView,(ClickableSpan)span);
      }
    }
    return null;
  }
 else {
    return null;
  }
}
