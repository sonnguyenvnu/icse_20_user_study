/** 
 * Overridden to damage the correct width of the caret, since this caret can be different sizes.
 * @param r The current location of the caret.
 */
@Override protected synchronized void damage(Rectangle r){
  if (r != null) {
    validateWidth(r);
    x=r.x - 1;
    y=r.y;
    width=r.width + 4;
    height=r.height;
    repaint();
  }
}
