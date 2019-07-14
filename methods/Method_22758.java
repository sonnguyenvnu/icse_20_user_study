private Rectangle getCaretRectangle(int x,int y){
  TextAreaPainter painter=textArea.getPainter();
  Point origin=painter.getLocationOnScreen();
  int height=painter.getFontMetrics().getHeight();
  return new Rectangle(origin.x + x,origin.y + y,0,height);
}
