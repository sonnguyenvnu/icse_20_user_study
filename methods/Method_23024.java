/** 
 * Synthesized replacement for FontMetrics.getAscent(), which is dreadfully inaccurate and inconsistent across platforms.
 */
static public double getAscent(Graphics g){
  Graphics2D g2=(Graphics2D)g;
  FontRenderContext frc=g2.getFontRenderContext();
  return new TextLayout("H",g.getFont(),frc).getBounds().getHeight();
}
