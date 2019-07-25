/** 
 * Should be called when part of painter visual representation changes.
 * @param bounds part bounds
 */
public void repaint(final Rectangle bounds){
  repaint(bounds.x,bounds.y,bounds.width,bounds.height);
}
