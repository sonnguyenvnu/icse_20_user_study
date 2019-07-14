@Override protected void textLineImpl(char buffer[],int start,int stop,float x,float y){
  Font font=(Font)textFont.getNative();
  if (font != null) {
    Object antialias=g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
    if (antialias == null) {
      antialias=RenderingHints.VALUE_ANTIALIAS_DEFAULT;
    }
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,textFont.isSmooth() ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
    g2.setColor(fillColorObject);
    int length=stop - start;
    if (length != 0) {
      g2.drawChars(buffer,start,length,(int)(x + 0.5f),(int)(y + 0.5f));
    }
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,antialias);
  }
 else {
    super.textLineImpl(buffer,start,stop,x,y);
  }
}
