/** 
 * Handles scaling for high-res displays, also sets text anti-aliasing options to be far less ugly than the defaults. Moved to a utility function because it's used in several classes.
 * @return a Graphics2D object, as a bit o sugar
 */
static public Graphics2D prepareGraphics(Graphics g){
  Graphics2D g2=(Graphics2D)g;
  if (Toolkit.isRetina()) {
    g2.scale(2,2);
  }
  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
  g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
  if (Toolkit.isRetina()) {
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
  }
  zoomStroke(g2);
  return g2;
}
