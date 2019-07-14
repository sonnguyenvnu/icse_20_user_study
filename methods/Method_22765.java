private Point getCaretLocation(){
  int line=textArea.getCaretLine();
  int offsetX=composedBeginCaretPosition - textArea.getLineStartOffset(line);
  return new Point(textArea.offsetToX(line,offsetX),textArea.lineToY(line + 1));
}
