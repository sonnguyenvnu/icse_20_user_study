/** 
 * Installs component updater and ensures that it is the only installed
 */
public static ComponentUpdater install(final JComponent component){
  uninstall(component);
  return new ComponentUpdater(component);
}
