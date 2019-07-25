/** 
 * Installs WebLookAndFeel in one simple call and updates all existing components if requested.
 * @param updateExistingComponents whether update all existing components or not
 * @return true if WebLookAndFeel was successfuly installed, false otherwise
 */
public static boolean install(final boolean updateExistingComponents){
  if (LafUtils.setupLookAndFeelSafely(WebLookAndFeel.class)) {
    if (updateExistingComponents) {
      updateAllComponentUIs();
    }
    return true;
  }
 else {
    return false;
  }
}
