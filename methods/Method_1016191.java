/** 
 * Uninstalls all rollover selection adapters from the specified list.
 * @param list list to modify
 */
public static void uninstall(final JList list){
  for (  final MouseMotionListener listener : list.getMouseMotionListeners()) {
    if (listener instanceof ListRolloverSelectionAdapter) {
      list.removeMouseMotionListener(listener);
    }
  }
}
