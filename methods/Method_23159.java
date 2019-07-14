/** 
 * Same as parent, but override for native version of the font. <p/> Called from textFontImpl and textSizeImpl, so the metrics will get recorded properly.
 */
@Override protected void handleTextSize(float size){
  Font font=(Font)textFont.getNative();
  if (font != null) {
    if (font.getSize2D() != size) {
      Map<TextAttribute,Object> map=new HashMap<>();
      map.put(TextAttribute.SIZE,size);
      map.put(TextAttribute.KERNING,TextAttribute.KERNING_ON);
      font=font.deriveFont(map);
    }
    g2.setFont(font);
    textFont.setNative(font);
    fontObject=font;
  }
  super.handleTextSize(size);
}
