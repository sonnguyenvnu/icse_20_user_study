/** 
 * Paint the gutter: draw the background, draw line numbers, break points.
 * @param gfx the graphics context
 * @param line 0-based line number
 * @param x horizontal position
 */
protected void paintLeftGutter(Graphics gfx,int line,int x){
  int y=textArea.lineToY(line) + fm.getLeading() + fm.getMaxDescent();
  if (line == textArea.getSelectionStopLine()) {
    gfx.setColor(gutterLineHighlightColor);
    gfx.fillRect(0,y,Editor.LEFT_GUTTER,fm.getHeight());
  }
 else {
    gfx.setClip(0,y,Editor.LEFT_GUTTER,fm.getHeight());
    gfx.drawImage(((PdeTextArea)textArea).getGutterGradient(),0,0,getWidth(),getHeight(),this);
    gfx.setClip(null);
  }
  String text=null;
  if (getEditor().isDebuggerEnabled()) {
    text=getPdeTextArea().getGutterText(line);
  }
  gfx.setColor(line < textArea.getLineCount() ? gutterTextColor : gutterPastColor);
  int textRight=Editor.LEFT_GUTTER - Editor.GUTTER_MARGIN;
  int textBaseline=textArea.lineToY(line) + fm.getHeight();
  if (text != null) {
    if (text.equals(PdeTextArea.BREAK_MARKER)) {
      drawDiamond(gfx,textRight - 8,textBaseline - 8,8,8);
    }
 else     if (text.equals(PdeTextArea.STEP_MARKER)) {
      drawRightArrow(gfx,textRight - 7,textBaseline - 7.5f,7,7);
    }
  }
 else {
    text=String.valueOf(line + 1);
    gfx.setFont(gutterTextFont);
    char[] txt=text.toCharArray();
    int tx=textRight - gfx.getFontMetrics().charsWidth(txt,0,txt.length);
    Utilities.drawTabbedText(new Segment(txt,0,text.length()),tx,textBaseline,gfx,this,0);
  }
}
