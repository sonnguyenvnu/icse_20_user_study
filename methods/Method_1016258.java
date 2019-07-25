/** 
 * Uninstalls any existing component updater from component
 */
public static void uninstall(final JComponent component){
  for (  final AncestorListener listener : component.getAncestorListeners()) {
    if (listener instanceof ComponentUpdater) {
      component.removeAncestorListener(listener);
    }
  }
}
