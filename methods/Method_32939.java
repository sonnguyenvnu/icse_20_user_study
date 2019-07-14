private void drawTextRun(Canvas canvas,char[] text,int[] palette,float y,int startColumn,int runWidthColumns,int startCharIndex,int runWidthChars,float mes,int cursor,int cursorStyle,long textStyle,boolean reverseVideo){
  int foreColor=TextStyle.decodeForeColor(textStyle);
  final int effect=TextStyle.decodeEffect(textStyle);
  int backColor=TextStyle.decodeBackColor(textStyle);
  final boolean bold=(effect & (TextStyle.CHARACTER_ATTRIBUTE_BOLD | TextStyle.CHARACTER_ATTRIBUTE_BLINK)) != 0;
  final boolean underline=(effect & TextStyle.CHARACTER_ATTRIBUTE_UNDERLINE) != 0;
  final boolean italic=(effect & TextStyle.CHARACTER_ATTRIBUTE_ITALIC) != 0;
  final boolean strikeThrough=(effect & TextStyle.CHARACTER_ATTRIBUTE_STRIKETHROUGH) != 0;
  final boolean dim=(effect & TextStyle.CHARACTER_ATTRIBUTE_DIM) != 0;
  if ((foreColor & 0xff000000) != 0xff000000) {
    if (bold && foreColor >= 0 && foreColor < 8)     foreColor+=8;
    foreColor=palette[foreColor];
  }
  if ((backColor & 0xff000000) != 0xff000000) {
    backColor=palette[backColor];
  }
  final boolean reverseVideoHere=reverseVideo ^ (effect & (TextStyle.CHARACTER_ATTRIBUTE_INVERSE)) != 0;
  if (reverseVideoHere) {
    int tmp=foreColor;
    foreColor=backColor;
    backColor=tmp;
  }
  float left=startColumn * mFontWidth;
  float right=left + runWidthColumns * mFontWidth;
  mes=mes / mFontWidth;
  boolean savedMatrix=false;
  if (Math.abs(mes - runWidthColumns) > 0.01) {
    canvas.save();
    canvas.scale(runWidthColumns / mes,1.f);
    left*=mes / runWidthColumns;
    right*=mes / runWidthColumns;
    savedMatrix=true;
  }
  if (backColor != palette[TextStyle.COLOR_INDEX_BACKGROUND]) {
    mTextPaint.setColor(backColor);
    canvas.drawRect(left,y - mFontLineSpacingAndAscent + mFontAscent,right,y,mTextPaint);
  }
  if (cursor != 0) {
    mTextPaint.setColor(cursor);
    float cursorHeight=mFontLineSpacingAndAscent - mFontAscent;
    if (cursorStyle == TerminalEmulator.CURSOR_STYLE_UNDERLINE)     cursorHeight/=4.;
 else     if (cursorStyle == TerminalEmulator.CURSOR_STYLE_BAR)     right-=((right - left) * 3) / 4.;
    canvas.drawRect(left,y - cursorHeight,right,y,mTextPaint);
  }
  if ((effect & TextStyle.CHARACTER_ATTRIBUTE_INVISIBLE) == 0) {
    if (dim) {
      int red=(0xFF & (foreColor >> 16));
      int green=(0xFF & (foreColor >> 8));
      int blue=(0xFF & foreColor);
      red=red * 2 / 3;
      green=green * 2 / 3;
      blue=blue * 2 / 3;
      foreColor=0xFF000000 + (red << 16) + (green << 8) + blue;
    }
    mTextPaint.setFakeBoldText(bold);
    mTextPaint.setUnderlineText(underline);
    mTextPaint.setTextSkewX(italic ? -0.35f : 0.f);
    mTextPaint.setStrikeThruText(strikeThrough);
    mTextPaint.setColor(foreColor);
    canvas.drawText(text,startCharIndex,runWidthChars,left,y - mFontLineSpacingAndAscent,mTextPaint);
  }
  if (savedMatrix)   canvas.restore();
}
