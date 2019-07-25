/** 
 * Uninstalls all listeners from the specified tree.
 * @param tree tree to modify
 */
public static void uninstall(final JTree tree){
  for (  final TreeExpansionListener listener : tree.getTreeExpansionListeners()) {
    if (listener instanceof AutoExpandSingleChildNodeListener) {
      tree.removeTreeExpansionListener(listener);
    }
  }
}
