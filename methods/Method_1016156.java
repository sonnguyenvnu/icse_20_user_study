/** 
 * Should be called when painter visual representation changes.
 */
public void repaint(){
  for (  final PainterListener listener : CollectionUtils.copy(listeners)) {
    listener.repaint();
  }
}
