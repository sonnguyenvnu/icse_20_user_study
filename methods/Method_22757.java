public Rectangle getTextLocation(){
  Point caret=getCaretLocation();
  return getCaretRectangle(caret.x,caret.y);
}
