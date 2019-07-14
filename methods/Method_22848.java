/** 
 * Paints the specified line onto the graphics context. Note that this method munges the offset and count values of the segment.
 * @param line The line segment
 * @param tokens The token list for the line
 * @param styles The syntax style list
 * @param expander The tab expander used to determine tab stops. Maybe null
 * @param gfx The graphics context
 * @param x The x co-ordinate
 * @param y The y co-ordinate
 * @return The x co-ordinate, plus the width of the painted string
 */
protected int paintSyntaxLine(Graphics gfx,Segment line,int x,int y,Token tokens,SyntaxStyle[] styles){
  int x0=x - textArea.getHorizontalOffset();
  for (; ; ) {
    byte id=tokens.id;
    if (id == Token.END)     break;
    int length=tokens.length;
    if (id == Token.NULL) {
      gfx.setColor(defaults.fgcolor);
      gfx.setFont(plainFont);
    }
 else {
      SyntaxStyle ss=styles[id];
      gfx.setColor(ss.getColor());
      gfx.setFont(ss.isBold() ? boldFont : plainFont);
    }
    line.count=length;
    for (int i=0; i < line.count; i++) {
      gfx.drawChars(line.array,line.offset + i,1,x,y);
      x=line.array[line.offset + i] == '\t' ? x0 + (int)nextTabStop(x - x0,i) : x + fm.charWidth(line.array[line.offset + i]);
    }
    line.offset+=length;
    tokens=tokens.next;
  }
  return x;
}
