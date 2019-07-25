/** 
 * Uninstalls all rollover selection adapters from the specified tree.
 * @param tree tree to modify
 */
public static void uninstall(final JTree tree){
  for (  final MouseMotionListener listener : tree.getMouseMotionListeners()) {
    if (listener instanceof TreeRolloverSelectionAdapter) {
      tree.removeMouseMotionListener(listener);
    }
  }
}
