/** 
 * Should be called when part of painter visual representation changes.
 * @param x      part bounds X coordinate
 * @param y      part bounds Y coordinate
 * @param width  part bounds width
 * @param height part bounds height
 */
public void repaint(final int x,final int y,final int width,final int height){
  for (  final PainterListener listener : CollectionUtils.copy(listeners)) {
    listener.repaint(x,y,width,height);
  }
}
