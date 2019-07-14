protected void paintSyntaxLine(Graphics gfx,int line,int x,int y,TokenMarkerState tokenMarker){
  textArea.getLineText(currentLineIndex,currentLine);
  currentLineTokens=tokenMarker.markTokens(currentLine,currentLineIndex);
  paintHighlight(gfx,line,y);
  y+=fm.getHeight();
  x=paintSyntaxLine(gfx,currentLine,x,y,currentLineTokens,defaults.styles);
  if (compositionTextPainter != null && compositionTextPainter.hasComposedTextLayout()) {
    compositionTextPainter.draw(gfx,defaults.lineHighlightColor);
  }
  if (defaults.eolMarkers) {
    gfx.setColor(defaults.eolMarkerColor);
    gfx.drawString(".",x,y);
  }
}
