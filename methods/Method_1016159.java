/** 
 * Should be called when painter size or border changes.
 */
public void revalidate(){
  for (  final PainterListener listener : CollectionUtils.copy(listeners)) {
    listener.revalidate();
  }
}
