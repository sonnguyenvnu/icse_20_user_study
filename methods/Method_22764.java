/** 
 * Draw text via input method with composed text information. This method can draw texts with some underlines to illustrate converting characters. This method is workaround for TextAreaPainter. Because, TextAreaPainter can't treat AttributedCharacterIterator directly. AttributedCharacterIterator has very important information when composing text. It has a map where are converted characters and committed characters. Ideally, changing TextAreaPainter method can treat AttributedCharacterIterator is better. But it's very tough!! So I choose to write some code as a workaround. This draw method is proceeded with the following steps. 1. Original TextAreaPainter draws characters. 2. CompositionTextPainter.draw method paints composed text. It was actually drawn by TextLayout.
 * @param gfx set TextAreaPainter's Graphics object.
 * @param fillBackGroundColor set textarea's background.
 */
public void draw(Graphics gfx,Color fillBackGroundColor){
  if (composedTextLayout != null) {
    Point composedLoc=getCaretLocation();
    composedTextLayout.draw((Graphics2D)gfx,composedLoc.x,composedLoc.y);
    if (this.caret != null) {
      int caretLocation=Math.round(composedTextLayout.getCaretInfo(caret)[0]);
      Rectangle rect=new Rectangle(composedLoc.x + caretLocation,composedLoc.y - (int)composedTextLayout.getAscent(),1,(int)composedTextLayout.getAscent() + (int)composedTextLayout.getDescent());
      Color c=gfx.getColor();
      if (caretColorFlag) {
        caretColorFlag=false;
        gfx.setColor(Color.WHITE);
      }
 else {
        caretColorFlag=true;
        gfx.setColor(Color.BLACK);
      }
      gfx.fillRect(rect.x,rect.y,rect.width,rect.height);
      gfx.setColor(c);
    }
  }
}
