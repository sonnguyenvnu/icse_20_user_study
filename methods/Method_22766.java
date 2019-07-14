@Override public Rectangle getTextLocation(TextHitInfo offset){
  if (Base.DEBUG) {
    Messages.log("#Called getTextLocation:" + offset);
  }
  int line=textArea.getCaretLine();
  int offsetX=textArea.getCaretPosition() - textArea.getLineStartOffset(line);
  Rectangle rectangle=new Rectangle(textArea.offsetToX(line,offsetX),textArea.lineToY(line + 1),0,0);
  Point location=textArea.getPainter().getLocationOnScreen();
  rectangle.translate(location.x,location.y);
  return rectangle;
}
