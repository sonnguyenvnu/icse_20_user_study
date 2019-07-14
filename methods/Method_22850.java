protected void paintBracketHighlight(Graphics gfx,int line,int y){
  int position=textArea.getBracketPosition();
  if (position != -1) {
    y+=fm.getLeading() + fm.getMaxDescent();
    int x=textArea._offsetToX(line,position);
    gfx.setColor(defaults.bracketHighlightColor);
    gfx.drawRect(x,y,fm.charWidth('(') - 1,fm.getHeight() - 1);
  }
}
