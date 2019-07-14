/** 
 * Support for http://vt100.net/docs/vt510-rm/DECCARA and http://vt100.net/docs/vt510-rm/DECCARA 
 */
public void setOrClearEffect(int bits,boolean setOrClear,boolean reverse,boolean rectangular,int leftMargin,int rightMargin,int top,int left,int bottom,int right){
  for (int y=top; y < bottom; y++) {
    TerminalRow line=mLines[externalToInternalRow(y)];
    int startOfLine=(rectangular || y == top) ? left : leftMargin;
    int endOfLine=(rectangular || y + 1 == bottom) ? right : rightMargin;
    for (int x=startOfLine; x < endOfLine; x++) {
      long currentStyle=line.getStyle(x);
      int foreColor=TextStyle.decodeForeColor(currentStyle);
      int backColor=TextStyle.decodeBackColor(currentStyle);
      int effect=TextStyle.decodeEffect(currentStyle);
      if (reverse) {
        effect=(effect & ~bits) | (bits & ~effect);
      }
 else       if (setOrClear) {
        effect|=bits;
      }
 else {
        effect&=~bits;
      }
      line.mStyle[x]=TextStyle.encode(foreColor,backColor,effect);
    }
  }
}
