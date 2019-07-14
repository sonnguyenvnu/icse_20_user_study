private void handleTextOffsetChange(MotionEvent event){
  final Rect bounds=getBounds();
  final int x=(int)event.getX() - bounds.left;
  final int y=(int)event.getY() - bounds.top;
  final int offset=getTextOffsetAt(x,y);
  if (offset >= 0 && offset <= mText.length()) {
    mTextOffsetOnTouchListener.textOffsetOnTouch(offset);
  }
}
