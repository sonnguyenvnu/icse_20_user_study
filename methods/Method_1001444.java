/** 
 * Paints the specified text into the current <tt>Graphics</tt> context. <p> If the font size is zero the default font size will be used. <p> If the orientation angle is unequal zero the text will first be painted into an off-screen image and rotated. Finally, it will be drawn into the current <tt>Graphics</tt> context. Note, that only integer multiples of 90 degree rotation are performed. Other orientation angles will be adjusted to the nearest integer multiple of 90 degree.
 */
public void render(Text text){
  final GraphicAttributes ga=text.getGraphicAttributes();
  if (ga instanceof TextAttributes) {
    final TextAttributes ta=(TextAttributes)ga;
    final Color currentColor=_graphics.getColor();
    Color fontColor=ta.getTextColor();
    if (fontColor == null) {
      fontColor=_defaultColor;
    }
    _graphics.setColor(fontColor);
    final double scale=_graphics.getTransform().getScaleX();
    final String str=text.getText();
    AffineTransform before=_graphics.getTransform();
    _graphics.setTransform(new AffineTransform());
    double fs=ta.getFontSize();
    fs=fs == 0 ? 1 : fs * scale / DEFAULT_FONT_SIZE;
    Font font=createFont(ta,0);
    AffineTransform fontTransform=new AffineTransform();
    fontTransform.scale(fs,fs);
    fontTransform.rotate(-ta.getOrientationAngle() * Math.PI / 180);
    font=font.deriveFont(fontTransform);
    _graphics.setFont(font);
    Rectangle2D bounds=_graphics.getFontMetrics().getStringBounds(str,_graphics);
    fontTransform.rotate(-ta.getOrientationAngle() * Math.PI / 180);
    final double yy=bounds.getHeight() + bounds.getY();
    Point2D.Double pos=new Point2D.Double(text.getPosition().getX(),text.getPosition().getY());
    before.transform(pos,pos);
    double x=0;
    double y=0;
    if (ta.getOrientationAngle() == 0) {
      x=-0.5 * ta.getHorizontalAnchor().getFactor() * bounds.getWidth();
      y=0.5 * ta.getVerticalAnchor().getFactor() * bounds.getHeight() - yy;
      x=pos.x + x;
      y=pos.y + y;
    }
 else {
      x=0.5 * ta.getVerticalAnchor().getFactor() * bounds.getHeight();
      y=0.5 * ta.getHorizontalAnchor().getFactor() * bounds.getWidth();
      x=pos.x + x;
      y=pos.y + y;
    }
    _graphics.drawString(str,(float)x,(float)y);
    _graphics.setTransform(before);
    _graphics.setColor(currentColor);
  }
}
