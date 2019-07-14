/** 
 * Read and apply new values from the preferences, either because the app is just starting up, or the user just finished messing with things in the Preferences window.
 */
protected void applyPreferences(){
  textarea.getPainter().updateAppearance();
  textarea.repaint();
  console.updateAppearance();
}
