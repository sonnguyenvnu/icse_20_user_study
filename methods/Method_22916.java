public void highlight(int tabIndex,int startOffset,int stopOffset){
  toFront();
  sketch.setCurrentCode(tabIndex);
  int length=textarea.getDocumentLength();
  startOffset=PApplet.constrain(startOffset,0,length);
  stopOffset=PApplet.constrain(stopOffset,0,length);
  textarea.select(startOffset,stopOffset);
  textarea.scrollToCaret();
  repaint();
}
